package edu.udel.cis.vsl.civl.library.common.stdio;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.udel.cis.vsl.civl.err.CIVLExecutionException;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.Certainty;
import edu.udel.cis.vsl.civl.err.CIVLExecutionException.ErrorKind;
import edu.udel.cis.vsl.civl.err.CIVLInternalException;
import edu.udel.cis.vsl.civl.err.CIVLSyntaxException;
import edu.udel.cis.vsl.civl.err.CIVLUnimplementedFeatureException;
import edu.udel.cis.vsl.civl.err.UnsatisfiablePathConditionException;
import edu.udel.cis.vsl.civl.library.IF.LibraryExecutor;
import edu.udel.cis.vsl.civl.library.common.CommonLibraryExecutor;
import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.Identifier;
import edu.udel.cis.vsl.civl.model.IF.Model;
import edu.udel.cis.vsl.civl.model.IF.ModelFactory;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.statement.CallOrSpawnStatement;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLPointerType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLStructOrUnionType;
import edu.udel.cis.vsl.civl.model.IF.type.CIVLType;
import edu.udel.cis.vsl.civl.semantics.IF.Evaluation;
import edu.udel.cis.vsl.civl.semantics.IF.Executor;
import edu.udel.cis.vsl.civl.state.IF.State;
import edu.udel.cis.vsl.civl.util.Pair;
import edu.udel.cis.vsl.sarl.IF.Reasoner;
import edu.udel.cis.vsl.sarl.IF.ValidityResult.ResultType;
import edu.udel.cis.vsl.sarl.IF.expr.ArrayElementReference;
import edu.udel.cis.vsl.sarl.IF.expr.BooleanExpression;
import edu.udel.cis.vsl.sarl.IF.expr.NumericExpression;
import edu.udel.cis.vsl.sarl.IF.expr.ReferenceExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicConstant;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression;
import edu.udel.cis.vsl.sarl.IF.expr.SymbolicExpression.SymbolicOperator;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicArrayType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicTupleType;
import edu.udel.cis.vsl.sarl.IF.type.SymbolicType;
import edu.udel.cis.vsl.sarl.collections.IF.SymbolicSequence;

/**
 * Executor for stdio function calls. Some methods may be used elsewhere so this
 * executor may be loaded even if the user program has not included stdio. (See
 * "Other Public Methods".)
 * 
 * The following system functions are defined here:
 * <ul>
 * <li><code>$filesystem $filesystem_create($scope)</code></li>
 * <li><code>CIVL_File_mode CIVL_File_stringToMode(char *)</code></li>
 * <li><code>void $filesystem_destroy($filesystem)</code></li>
 * <li>
 * <code>FILE * $fopen($filesystem, const char * restrict, CIVL_File_mode)</code>
 * </li>
 * <li><code>int fclose(FILE *)</code></li>
 * <li><code>int fflush(FILE *)</code></li>
 * <li><code>int fprintf(FILE * restrict, const char * restrict, ...)</code></li>
 * <li><code>int fscanf(FILE * restrict, const char * restrict, ...)</code></li>
 * <li><code>void $filesystem_copy_output($filesystem, $file *)</code></li>
 * </ul>
 * 
 * Occurrences of functions <code>printf</code> and <code>scanf</code> in the
 * original source will already have been replaced by calls to
 * <code>fprintf</code> and <code>fscanf</code>, respectively.
 * 
 * fscanf: $assume $testFileLength("foo") == n*m+k; must appear before any
 * opening of the file.
 * 
 * C transformer: civl pragma's
 * 
 * @author Stephen F. Siegel (siegel)
 * @author Ziqing Luo (ziqing)
 * @author Manchun Zheng (zmanchun)
 * 
 */
public class LibstdioExecutor extends CommonLibraryExecutor implements
		LibraryExecutor {

	// the different file modes; see stdio.cvl:
	public final static int CIVL_FILE_MODE_R = 0;
	public final static int CIVL_FILE_MODE_W = 1;
	public final static int CIVL_FILE_MODE_WX = 2;
	public final static int CIVL_FILE_MODE_A = 3;
	public final static int CIVL_FILE_MODE_RB = 4;
	public final static int CIVL_FILE_MODE_WB = 5;
	public final static int CIVL_FILE_MODE_WBX = 6;
	public final static int CIVL_FILE_MODE_AB = 7;
	public final static int CIVL_FILE_MODE_RP = 8;
	public final static int CIVL_FILE_MODE_WP = 9;
	public final static int CIVL_FILE_MODE_WPX = 10;
	public final static int CIVL_FILE_MODE_AP = 11;
	public final static int CIVL_FILE_MODE_RPB = 12;
	public final static int CIVL_FILE_MODE_WPB = 13;
	public final static int CIVL_FILE_MODE_WPBX = 14;
	public final static int CIVL_FILE_MODE_APB = 15;

	// file name of stdout/stdin
	public final static String STDOUT = "CIVL_stdout";
	public final static String STDIN = "CIVL_stdin";
	public final static String STDERR = "CIVL_stderr";

	/**
	 * The base type of the pointer type $filesystem; a structure type with
	 * fields (0) scope, and (1) files.
	 */
	private CIVLStructOrUnionType filesystemStructType;

	/**
	 * The symbolic type corresponding to filesystemStructType.
	 */
	private SymbolicTupleType filesystemStructSymbolicType;

	/**
	 * The CIVL struct type $file, defined in stdio.
	 */
	private CIVLStructOrUnionType fileType;

	/**
	 * The symbolic type corresponding to fileType.
	 */
	private SymbolicTupleType fileSymbolicType;

	/**
	 * The CIVL type FILE, defined in stdio.
	 */
	private CIVLStructOrUnionType FILEtype;

	/**
	 * The symbolic type array-of-char (char[]).
	 */
	private SymbolicArrayType stringSymbolicType;

	/**
	 * Empty file contents: array of string of length 0.
	 */
	private SymbolicExpression emptyContents;

	/**
	 * Abstract function for the initial content of a file. Different files
	 * should have different initial content.
	 */
	private SymbolicConstant initialContentsFunction;

	private SymbolicConstant fileLengthFunction;

	/**
	 * Abstract function to convert an integer into a string with a format:
	 * <code>char* intToString(char* format, int data)</code>.
	 */
	private SymbolicConstant intToStringFunction;

	/**
	 * Abstract function to convert a double into a string with a format:
	 * <code>char* doubleToString(char* format, double data)</code>.
	 */
	private SymbolicConstant doubleToStringFunction;

	/**
	 * Abstract function to convert a character into a string with a format:
	 * <code>char* charToString(char* format, char data)</code>.
	 */
	private SymbolicConstant charToStringFunction;

	/**
	 * Abstract function to convert a string into a string with a format:
	 * <code>char* stringDataToString(char* format, char* data)</code>.
	 */
	private SymbolicConstant stringDataToStringFunction;

	/**
	 * Abstract function to convert a pointer into a string with a format:
	 * <code>char* pointerToString(char* format, char data)</code>.
	 */
	private SymbolicConstant pointerToStringFunction;

	/**
	 * Abstract function to convert a string into an integer according to a
	 * format: <code>int stringToInt(char* format, char* string)</code>.
	 */
	private SymbolicConstant stringToIntFunction;

	/**
	 * Abstract function to convert a string into a double according to a
	 * format: <code>double stringToDouble(char* format, char* string)</code>.
	 */
	private SymbolicConstant stringToDoubleFunction;

	/**
	 * Abstract function to convert a string into a character according to a
	 * format: <code>char stringToChar(char* format, char* string)</code>.
	 */
	private SymbolicConstant stringToCharFunction;

	/**
	 * Abstract function to convert a string into a data of string type
	 * according to a format:
	 * <code>char* stringToStringData(char* format, char* string)</code>.
	 */
	private SymbolicConstant stringToStringDataFunction;

	/**
	 * Abstract function to convert a string into a pointer according to a
	 * format: <code>void* stringToPointer(char* format, char* string)</code>.
	 */
	private SymbolicConstant stringToPointerFunction;

	private SymbolicExpression EOF;

	/**
	 * The set of characters that are used to construct a number in a format
	 * string.
	 */
	private Set<Character> numbers;

	/* **************************** Constructors *************************** */

	/**
	 * Create a new instance of library executor for "stdio.h".
	 * 
	 * @param primaryExecutor
	 *            The main executor of the system.
	 * @param output
	 *            The output stream for printing.
	 * @param enablePrintf
	 *            True iff print is enabled, reflecting command line options.
	 */
	public LibstdioExecutor(Executor primaryExecutor, PrintStream output,
			PrintStream err, boolean enablePrintf, boolean statelessPrintf,
			ModelFactory modelFactory) {
		super(primaryExecutor, output, err, enablePrintf, statelessPrintf,
				modelFactory);
		Model model = modelFactory.model();
		SymbolicType stringArrayType;

		EOF = universe.canonic(universe.integer(-100));
		stringSymbolicType = (SymbolicArrayType) universe.canonic(universe
				.arrayType(universe.characterType()));
		stringArrayType = (SymbolicArrayType) universe.canonic(universe
				.arrayType(stringSymbolicType));
		emptyContents = universe.canonic(universe
				.emptyArray(stringSymbolicType));
		initialContentsFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("contents"), universe
						.functionType(Arrays.asList(stringSymbolicType),
								stringArrayType)));
		fileLengthFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("fileLength"), universe
						.functionType(Arrays.asList(stringSymbolicType),
								universe.integerType())));
		createStringToDataFunctions();
		createDataToStringFunctions();
		// createCharReadFunctions();
		this.filesystemStructType = model.basedFilesystemType();
		if (filesystemStructType != null)
			this.filesystemStructSymbolicType = (SymbolicTupleType) this.filesystemStructType
					.getDynamicType(universe);
		this.fileType = model.fileType();
		if (fileType != null)
			this.fileSymbolicType = (SymbolicTupleType) this.fileType
					.getDynamicType(universe);
		this.FILEtype = model.FILEtype();
		numbers = new HashSet<Character>(10);
		for (int i = 0; i < 10; i++) {
			numbers.add(Character.forDigit(i, 10));
		}
	}

	/* ************************** Private Methods ************************** */

	/**
	 * // * This is a helper function of the constructor. It initializes all the
	 * // * carType/cdrType abstract functions. //
	 */
	// private void createCharReadFunctions() {
	// carIntFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("carInt"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// cdrIntFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("cdrInt"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// carDoubleFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("carDouble"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// cdrDoubleFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("cdrDouble"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// carCharFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("carChar"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// cdrCharFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("cdrChar"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// carPointerFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("carPointer"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// cdrPointerFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("cdrPointer"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// carStringFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("carString"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// cdrStringFunction = (SymbolicConstant) universe.canonic(universe
	// .symbolicConstant(universe.stringObject("cdrString"), universe
	// .functionType(Arrays.asList(stringSymbolicType,
	// stringSymbolicType), stringSymbolicType)));
	// }

	/**
	 * This is a helper function of the constructor. It initializes all the
	 * abstract functions to convert a data of a certain type into a string.
	 */
	private void createDataToStringFunctions() {
		intToStringFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("intToString"),
						universe.functionType(
								Arrays.asList(stringSymbolicType,
										universe.integerType()),
								stringSymbolicType)));
		doubleToStringFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("doubleToString"),
						universe.functionType(
								Arrays.asList(stringSymbolicType,
										universe.realType()),
								stringSymbolicType)));
		charToStringFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("charToString"),
						universe.functionType(
								Arrays.asList(stringSymbolicType,
										universe.characterType()),
								stringSymbolicType)));
		stringDataToStringFunction = (SymbolicConstant) universe
				.canonic(universe.symbolicConstant(universe
						.stringObject("stringDataToString"), universe
						.functionType(Arrays.asList(stringSymbolicType,
								stringSymbolicType), stringSymbolicType)));
		pointerToStringFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("pointerToString"),
						universe.functionType(
								Arrays.asList(stringSymbolicType,
										modelFactory.pointerSymbolicType()),
								stringSymbolicType)));
	}

	/**
	 * This is a helper function of the constructor. It initializes all the
	 * abstract functions to convert a string to a data of certain type.
	 */
	private void createStringToDataFunctions() {
		stringToIntFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("stringToInt"),
						universe.functionType(Arrays.asList(stringSymbolicType,
								stringSymbolicType), universe.integerType())));
		stringToDoubleFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("stringToDouble"),
						universe.functionType(Arrays.asList(stringSymbolicType,
								stringSymbolicType), universe.realType())));
		stringToCharFunction = (SymbolicConstant) universe
				.canonic(universe.symbolicConstant(universe
						.stringObject("stringToChar"), universe.functionType(
						Arrays.asList(stringSymbolicType, stringSymbolicType),
						universe.characterType())));
		stringToStringDataFunction = (SymbolicConstant) universe
				.canonic(universe.symbolicConstant(universe
						.stringObject("stringToStringData"), universe
						.functionType(Arrays.asList(stringSymbolicType,
								stringSymbolicType), stringSymbolicType)));
		stringToPointerFunction = (SymbolicConstant) universe.canonic(universe
				.symbolicConstant(universe.stringObject("stringToPointer"),
						universe.functionType(Arrays.asList(stringSymbolicType,
								stringSymbolicType), modelFactory
								.pointerSymbolicType())));
	}

	/* *************************** Private Methods ************************* */

	// private SymbolicExpression intToString(SymbolicExpression format,
	// SymbolicExpression data) {
	// return universe.apply(intToStringFunction, Arrays.asList(format, data));
	// }
	//
	// private SymbolicExpression doubleToString(SymbolicExpression format,
	// SymbolicExpression data) {
	// return universe.apply(doubleToStringFunction,
	// Arrays.asList(format, data));
	// }
	//
	// private SymbolicExpression charToString(SymbolicExpression format,
	// SymbolicExpression data) {
	// return universe
	// .apply(charToStringFunction, Arrays.asList(format, data));
	// }
	//
	// private SymbolicExpression stringToString(SymbolicExpression format,
	// SymbolicExpression data) {
	// return universe.apply(stringToStringFunction,
	// Arrays.asList(format, data));
	// }

	// private SymbolicExpression pointerToString(SymbolicExpression format,
	// SymbolicExpression data) {
	// return universe.apply(pointerToStringFunction,
	// Arrays.asList(format, data));
	// }
	//
	// private SymbolicExpression stringToInt(SymbolicExpression content) {
	// return universe.apply(stringToIntFunction, Arrays.asList(content));
	// }
	//
	// /**
	// * Apply the abstract function:
	// * <code>char* carInt(char** fileContent, char* format)</code>
	// *
	// * @param fileContent
	// * @param format
	// * @return
	// */
	// private SymbolicExpression carInt(SymbolicExpression fileContent,
	// SymbolicExpression format) {
	// return universe.apply(this.carIntFunction,
	// Arrays.asList(fileContent, format));
	// }

	// /**
	// * Apply the abstract function:
	// * <code>char** cdrInt(char** fileContent, char* format)</code>
	// *
	// * @param fileContent
	// * @param format
	// * @return
	// */
	// private SymbolicExpression cdrInt(SymbolicExpression fileContent,
	// SymbolicExpression format) {
	// return universe.apply(this.cdrIntFunction,
	// Arrays.asList(fileContent, format));
	// }
	//
	// /**
	// * Apply the abstract function:
	// * <code>char* carInt(char** fileContent, char* format)</code>
	// *
	// * @param fileContent
	// * @param string
	// * @return
	// */
	// private SymbolicExpression carString(SymbolicExpression fileContent,
	// SymbolicExpression string) {
	// return universe.apply(this.carStringFunction,
	// Arrays.asList(fileContent, string));
	// }
	//
	// /**
	// * Apply the abstract function:
	// * <code>char** cdrInt(char** fileContent, char* format)</code>
	// *
	// * @param fileContent
	// * @param string
	// * @return
	// */
	// private SymbolicExpression cdrString(SymbolicExpression fileContent,
	// SymbolicExpression string) {
	// return universe.apply(this.cdrStringFunction,
	// Arrays.asList(fileContent, string));
	// }

	/**
	 * Given a symbolic expression of type array of char, returns a string
	 * representation. If it is a concrete array of char consisting of concrete
	 * characters, this will be the obvious string. Otherwise the result is
	 * something readable but unspecified.
	 * 
	 * @throws UnsatisfiablePathConditionException
	 */
	private Pair<State, StringBuffer> getString(CIVLSource source, State state,
			SymbolicExpression charPointer)
			throws UnsatisfiablePathConditionException {
		if (charPointer.operator() == SymbolicOperator.CONCRETE) {
			SymbolicSequence<?> originalArray;
			int int_arrayIndex;
			StringBuffer result = new StringBuffer();
			int numChars;
			char[] stringChars;

			if (charPointer.type() instanceof SymbolicArrayType) {
				originalArray = (SymbolicSequence<?>) charPointer.argument(0);
				int_arrayIndex = 0;
			} else {
				SymbolicExpression arrayPointer = evaluator.parentPointer(
						source, charPointer);
				ArrayElementReference arrayRef = (ArrayElementReference) evaluator
						.getSymRef(charPointer);
				NumericExpression arrayIndex = arrayRef.getIndex();
				Evaluation eval = evaluator.dereference(source, state,
						arrayPointer);

				state = eval.state;
				originalArray = (SymbolicSequence<?>) eval.value.argument(0);
				int_arrayIndex = evaluator.extractInt(source, arrayIndex);
			}
			numChars = originalArray.size() - 1;// ignoring the '\0' at the end
												// of the string.
			stringChars = new char[numChars - int_arrayIndex];
			for (int i = 0, j = int_arrayIndex; j < numChars; i++, j++) {
				SymbolicExpression charExpr = originalArray.get(j);
				Character theChar = universe.extractCharacter(charExpr);

				if (theChar == null)
					throw new CIVLUnimplementedFeatureException(
							"non-concrete character in string at position " + j,
							source);
				stringChars[i] = theChar;
			}
			result.append(stringChars);
			return new Pair<>(state, result);
		} else
			throw new CIVLUnimplementedFeatureException("non-concrete strings",
					source);
	}

	/**
	 * Returns the symbolic expression representing the initial contents of a
	 * file named filename. This is the array of length 1 whose sole element is
	 * the expression "initialContents(filename)", which is the application of
	 * the abstract function initialContentsFunction to the filename.
	 * 
	 * @param filename
	 *            symbolic expression of string type (i.e., an array of char)
	 * @return symbolic expression representing initial contents of that file
	 */
	private SymbolicExpression initialContents(SymbolicExpression filename) {
		return universe.apply(initialContentsFunction, Arrays.asList(filename));
	}

	private SymbolicExpression fileLength(SymbolicExpression filename) {
		return universe.apply(this.fileLengthFunction, Arrays.asList(filename));
	}

	/**
	 * $filesystem CIVL_filesystem = $filesystem_create($here); Creates a new
	 * empty file system, returning a handle to it. <br>
	 * $filesystem s$filesystem_create($scope scope);
	 * 
	 * typedef struct CIVL_filesystem { $scope scope; $file files[]; } *
	 * $filesystem;
	 * 
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private State execute_filesystem_create(CIVLSource source, State state,
			int pid, LHSExpression lhs, Expression[] expressions,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression scope = argumentValues[0];
		LinkedList<SymbolicExpression> filesystemComponents = new LinkedList<>();
		LinkedList<SymbolicExpression> fileArrayComponents = new LinkedList<>();
		SymbolicExpression filesArray = universe.array(fileSymbolicType,
				fileArrayComponents);
		SymbolicExpression filesystem;

		filesystemComponents.add(scope);
		filesystemComponents.add(filesArray);
		filesystem = universe.tuple(filesystemStructSymbolicType,
				filesystemComponents);
		state = primaryExecutor.malloc(source, state, pid, lhs, expressions[0],
				scope, filesystemStructType, filesystem);
		return state;
	}

	/**
	 * <pre>
	 * FILE * $fopen($filesystem fs, const char * restrict filename,
	 *               const char * restrict mode);
	 * </pre>
	 * 
	 */
	private State execute_fopen(CIVLSource source, State state, int pid,
			LHSExpression lhs, Expression[] expressions,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression filesystemPointer = argumentValues[0];
		Evaluation eval = evaluator.dereference(expressions[0].getSource(),
				state, filesystemPointer);
		CIVLSource modeSource = expressions[2].getSource();
		int mode = evaluator.extractInt(modeSource,
				(NumericExpression) argumentValues[2]);
		SymbolicExpression fileSystemStructure;
		SymbolicExpression fileArray;
		SymbolicExpression filename;
		SymbolicSequence<?> fileSequence;
		int numFiles;
		int fileIndex;
		SymbolicExpression length;
		NumericExpression isInput, isOutput, isBinary, isWide = this.zero;
		SymbolicExpression contents;
		SymbolicExpression theFile;
		NumericExpression pos0 = zero, pos1 = zero;
		boolean isInputFile = false;
		int scopeId = evaluator.getScopeId(expressions[0].getSource(),
				filesystemPointer);
		int filesystemVid = evaluator.getVariableId(expressions[0].getSource(),
				filesystemPointer);
		ReferenceExpression fileSystemRef = evaluator
				.getSymRef(filesystemPointer);
		Pair<State, StringBuffer> fileNameStringPair;
		String fileNameString;

		state = eval.state;
		fileSystemStructure = eval.value;
		fileArray = universe.tupleRead(fileSystemStructure, oneObject);
		eval = evaluator.getStringExpression(state, expressions[1].getSource(),
				argumentValues[1]);
		state = eval.state;
		filename = eval.value;
		fileNameStringPair = this.getString(expressions[1].getSource(), state,
				argumentValues[1]);
		state = fileNameStringPair.left;
		fileNameString = fileNameStringPair.right.toString();

		// does a file by that name already exist in the filesystem?
		// assume all are concrete.
		if (fileArray.operator() != SymbolicOperator.CONCRETE)
			throw new CIVLUnimplementedFeatureException(
					"non-concrete file system", expressions[0]);
		fileSequence = (SymbolicSequence<?>) fileArray.argument(0);
		numFiles = fileSequence.size();
		for (fileIndex = 0; fileIndex < numFiles; fileIndex++) {
			SymbolicExpression tmpFile = fileSequence.get(fileIndex);
			SymbolicExpression tmpFilename = universe.tupleRead(tmpFile,
					zeroObject);

			if (tmpFilename.equals(filename)) {
				theFile = tmpFile;
				break;
			}
		}
		if (fileIndex == numFiles) {
			// file not found: create it.
			switch (mode) {
			case CIVL_FILE_MODE_R:
				// assume file exists with unconstrained contents
				isInput = this.one;
				isInputFile = true;
				isOutput = this.zero;
				isBinary = zero;
				contents = initialContents(filename);
				pos0 = pos1 = zero;
				break;
			case CIVL_FILE_MODE_W:
			case CIVL_FILE_MODE_WX:
				// assume file does not yet exist
				isInput = zero;
				isOutput = one;
				isBinary = zero;
				contents = emptyContents;
				pos0 = pos1 = zero;
				break;
			case CIVL_FILE_MODE_A:
				// assume file exists
				isInput = one;
				isInputFile = true;
				isOutput = one;
				isBinary = zero;
				contents = initialContents(filename);
				pos0 = one;
				pos1 = zero;
				break;
			case CIVL_FILE_MODE_RP:
				// assume file exists
				isInput = one;
				isInputFile = true;
				isOutput = one;
				isBinary = zero;
				contents = initialContents(filename);
				pos0 = pos1 = zero;
				break;
			default:
				throw new CIVLUnimplementedFeatureException(
						"FILE mode " + mode, modeSource);
			}
			length = this.fileLength(filename);
			theFile = universe.tuple(fileSymbolicType, Arrays.asList(filename,
					contents, isOutput, isInput, isBinary, isWide, length));
			fileArray = universe.append(fileArray, theFile);
			fileSystemStructure = universe.tupleWrite(fileSystemStructure,
					oneObject, fileArray);
			if (fileNameString.equals(STDIN))
				isInputFile = false;
			if (isInputFile) {
				BooleanExpression positiveLength = universe.lessThan(zero,
						(NumericExpression) length);

				state = state.setPathCondition(universe.and(
						state.getPathCondition(), positiveLength));
			}
			state = primaryExecutor.assign(expressions[1].getSource(), state,
					filesystemPointer, fileSystemStructure);
		}
		// now theFile is the new file and fileIndex is its index
		// malloc a new FILE object with appropriate pointers
		// create a pointer to theFile (array element reference)
		//
		{
			List<SymbolicExpression> streamComponents = new LinkedList<>();
			ReferenceExpression ref = universe.arrayElementReference(
					universe.tupleComponentReference(fileSystemRef, oneObject),
					universe.integer(fileIndex));
			SymbolicExpression filePointer = evaluator.makePointer(scopeId,
					filesystemVid, ref);
			SymbolicExpression fileStream;
			SymbolicExpression scope = modelFactory.scopeValue(state
					.getProcessState(pid).getDyscopeId());

			streamComponents.add(filePointer);
			streamComponents.add(filesystemPointer);
			streamComponents.add(pos0);
			streamComponents.add(pos1);
			streamComponents.add(argumentValues[2]);
			streamComponents.add(universe.integer(1));
			fileStream = universe.tuple(
					(SymbolicTupleType) FILEtype.getDynamicType(universe),
					streamComponents);
			// do malloc, get pointer, do the assignments.
			// state = primaryExecutor.assign(state, pid, lhs, fileStream);
			state = primaryExecutor.malloc(source, state, pid, lhs,
					expressions[0], scope, FILEtype, fileStream);

		}
		return state;
	}

	/**
	 * Execute a function call statement for a certain process at a given state.
	 * 
	 * @param state
	 *            The current state.
	 * @param pid
	 *            The Id of the process that the call statement belongs to.
	 * @param statement
	 *            The call statement to be executed.
	 * @return The new state after executing the call statement.
	 * @throws UnsatisfiablePathConditionException
	 */
	private State executeWork(State state, int pid,
			CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		Identifier name;
		Expression[] arguments;
		SymbolicExpression[] argumentValues;
		int numArgs;
		CIVLSource source = statement.getSource();
		LHSExpression lhs = statement.lhs();

		numArgs = statement.arguments().size();
		name = statement.function().name();
		arguments = new Expression[numArgs];
		argumentValues = new SymbolicExpression[numArgs];
		for (int i = 0; i < numArgs; i++) {
			Evaluation eval;

			arguments[i] = statement.arguments().get(i);
			eval = evaluator.evaluate(state, pid, arguments[i]);
			argumentValues[i] = eval.value;
			state = eval.state;
		}
		switch (name.name()) {
		case "printf":
			state = primaryExecutor.executePrintf(state, pid, arguments,
					argumentValues);
			break;
		case "$fopen":
			state = execute_fopen(source, state, pid, lhs, arguments,
					argumentValues);
			break;
		case "$filesystem_create":
			state = execute_filesystem_create(source, state, pid, lhs,
					arguments, argumentValues);
			break;
		case "$filesystem_destroy":
			state = executeFree(state, pid, arguments, argumentValues, source);
			break;
		case "fclose":
			state = executeFree(state, pid, arguments, argumentValues, source);
			break;
		case "fprintf":
			state = execute_fprintf(source, state, pid, lhs, arguments,
					argumentValues);
			break;
		case "fscanf":
			state = execute_fscanf(source, state, pid, lhs, arguments,
					argumentValues);
			break;
		case "$filesystem_copy_output":
			state = execute_filesystem_copy_output(source, state, pid,
					arguments, argumentValues);
			break;
		case "$textFileLength":
			state = execute_text_file_length(source, state, pid, lhs,
					arguments, argumentValues);
			break;
		default:
			throw new CIVLUnimplementedFeatureException(name.name(), statement);

		}
		state = stateFactory.setLocation(state, pid, statement.target());
		return state;
	}

	private State execute_text_file_length(CIVLSource source, State state,
			int pid, LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		Expression fileSystemExpression = modelFactory
				.civlFilesystemVariableExpression();
		Evaluation eval = evaluator.evaluate(state, pid, fileSystemExpression);
		SymbolicExpression filesystemPointer;
		SymbolicExpression fileSystemStructure;
		SymbolicExpression fileArray;
		SymbolicSequence<?> fileSequence;
		SymbolicExpression filename;
		int numFiles;
		int fileIndex;
		SymbolicExpression theFile = null;
		SymbolicExpression length;

		filesystemPointer = eval.value;
		state = eval.state;
		eval = evaluator.dereference(fileSystemExpression.getSource(), state,
				filesystemPointer);
		state = eval.state;
		fileSystemStructure = eval.value;
		fileArray = universe.tupleRead(fileSystemStructure, oneObject);
		eval = evaluator.getStringExpression(state, arguments[0].getSource(),
				argumentValues[0]);
		state = eval.state;
		filename = eval.value;

		// does a file by that name already exist in the filesystem?
		// assume all are concrete.
		if (fileArray.operator() != SymbolicOperator.CONCRETE)
			throw new CIVLUnimplementedFeatureException(
					"non-concrete file system", arguments[0]);
		fileSequence = (SymbolicSequence<?>) fileArray.argument(0);
		numFiles = fileSequence.size();
		for (fileIndex = 0; fileIndex < numFiles; fileIndex++) {
			SymbolicExpression tmpFile = fileSequence.get(fileIndex);
			SymbolicExpression tmpFilename = universe.tupleRead(tmpFile,
					zeroObject);

			if (tmpFilename.equals(filename)) {
				theFile = tmpFile;
				break;
			}
		}
		if (fileIndex == numFiles) {
			// file not found: create it.
			NumericExpression isInput = this.zero;
			NumericExpression isOutput = this.zero;
			NumericExpression isBinary = zero;
			SymbolicExpression contents = initialContents(filename);
			length = this.fileLength(filename);
			SymbolicExpression isWide = this.zero;

			theFile = universe.tuple(fileSymbolicType, Arrays.asList(filename,
					contents, isOutput, isInput, isBinary, isWide, length));
			fileArray = universe.append(fileArray, theFile);
			fileSystemStructure = universe.tupleWrite(fileSystemStructure,
					oneObject, fileArray);
			state = primaryExecutor.assign(fileSystemExpression.getSource(),
					state, filesystemPointer, fileSystemStructure);
		} else {
			SymbolicExpression isBinary = universe.tupleRead(theFile,
					universe.intObject(4));

			if (!isBinary.equals(this.zero)) {
				throw new CIVLExecutionException(ErrorKind.OTHER,
						Certainty.CONCRETE, "The file "
								+ arguments[0].toString()
								+ " is not a text file.", source);
			}
			length = universe.tupleRead(theFile, universe.intObject(6));
		}
		if (lhs != null) {
			// int scopeId = evaluator.getScopeId(
			// fileSystemExpression.getSource(), filesystemPointer);
			// int filesystemVid = evaluator.getVariableId(
			// fileSystemExpression.getSource(), filesystemPointer);
			// ReferenceExpression fileSystemRef = evaluator
			// .getSymRef(filesystemPointer);
			// ReferenceExpression ref =
			// universe.tupleComponentReference(universe
			// .arrayElementReference(universe.tupleComponentReference(
			// fileSystemRef, oneObject), universe
			// .integer(fileIndex)), universe.intObject(6));
			// SymbolicExpression fileLengthPointer = evaluator.makePointer(
			// scopeId, filesystemVid, ref);

			state = primaryExecutor.assign(state, pid, lhs, length);
		}
		return state;
	}

	private State execute_filesystem_copy_output(CIVLSource source,
			State state, int pid, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression civlFileSystemPointer = argumentValues[0];
		SymbolicExpression arrayPointer = argumentValues[1];
		SymbolicExpression fileArray;
		Evaluation eval;
		NumericExpression length;
		ArrayElementReference fileArrayEleRef = (ArrayElementReference) universe
				.tupleRead(arrayPointer, twoObject);
		NumericExpression startIndex = fileArrayEleRef.getIndex();
		int length_int;
		SymbolicExpression scopeField = universe.tupleRead(arrayPointer,
				zeroObject), varField = universe.tupleRead(arrayPointer,
				oneObject);
		CIVLSource arraySource = arguments[1].getSource();

		eval = evaluator.dereference(arguments[0].getSource(), state,
				civlFileSystemPointer);
		state = eval.state;
		fileArray = universe.tupleRead(eval.value, oneObject);
		length = universe.length(fileArray);
		length_int = evaluator.extractInt(arguments[0].getSource(), length);
		for (int i = 0; i < length_int; i++) {
			NumericExpression fileArrayIndex = universe.integer(i);
			NumericExpression index = universe.add(startIndex, fileArrayIndex);
			ArrayElementReference arrayEleRef = universe.arrayElementReference(
					universe.identityReference(), index);
			SymbolicExpression currentPointer = universe.tuple(
					modelFactory.pointerSymbolicType(),
					Arrays.asList(new SymbolicExpression[] { scopeField,
							varField, arrayEleRef }));

			state = primaryExecutor.assign(arraySource, state, currentPointer,
					universe.arrayRead(fileArray, fileArrayIndex));
		}
		return state;
	}

	/**
	 * Execute the function call for fprintf
	 * <code>int fprintf(FILE * restrict stream,
	 * const char * restrict format, ...)</code>.
	 * 
	 * @param source
	 *            The source code element of the function call.
	 * @param state
	 *            The state where the function call happens.
	 * @param pid
	 *            The ID of the process that this function call belongs to.
	 * @param lhs
	 *            The left hand side of the function call.
	 * @param arguments
	 *            The list of CIVL expressions for the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The list of symbolic expressions representing the value of the
	 *            arguments of the function call.
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private State execute_fscanf(CIVLSource source, State state, int pid,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression fileStream;
		SymbolicExpression filePointer;
		Evaluation eval;
		SymbolicExpression fileObject;
		StringBuffer formatBuffer;
		Pair<State, StringBuffer> formatString;
		NumericExpression position;

		eval = evaluator.dereference(arguments[0].getSource(), state,
				argumentValues[0]);
		fileStream = eval.value;
		state = eval.state;
		filePointer = universe.tupleRead(fileStream, zeroObject);
		position = (NumericExpression) universe
				.tupleRead(fileStream, twoObject);
		eval = evaluator.dereference(arguments[0].getSource(), state,
				filePointer);
		state = eval.state;
		fileObject = eval.value;
		{// checks file length
			NumericExpression fileLength = (NumericExpression) universe
					.tupleRead(fileObject, universe.intObject(6));
			BooleanExpression positionExceedFileLength = universe
					.lessThanEquals(fileLength, position);
			Reasoner reasoner = universe.reasoner(state.getPathCondition());
			ResultType positionExceedFileLengthValid = reasoner.valid(
					positionExceedFileLength).getResultType();

			if (positionExceedFileLengthValid == ResultType.YES) {
				if (lhs != null) {
					state = primaryExecutor.assign(state, pid, lhs, this.EOF);
				}
				return state;
			}
		}
		formatString = this.getString(arguments[1].getSource(), state,
				argumentValues[1]);
		formatBuffer = formatString.right;
		state = formatString.left;

		{ // reads the file
			SymbolicExpression fileContents = universe.tupleRead(fileObject,
					oneObject);
			List<Format> formats = this.splitFormat(arguments[1].getSource(),
					formatBuffer);
			int numOfFormats = formats.size();
			int dataPointerIndex = 2;
			int count = 0;

			for (int i = 0; i < numOfFormats; i++) {
				Format currentFormat = formats.get(i);
				String formatValue = currentFormat.string.toString();
				SymbolicExpression currentString = universe.arrayRead(
						fileContents, position);
				SymbolicExpression format, data;
				// SymbolicConstant carFunction = null, cdrFunction = null;
				ConversionType conversion = currentFormat.type;
				SymbolicConstant conversionFunction = null;

				format = universe.stringExpression(formatValue);
				switch (conversion) {
				case INT:
					conversionFunction = this.stringToIntFunction;
					break;
				case DOUBLE:
					conversionFunction = this.stringToDoubleFunction;
					break;
				case POINTER:
					conversionFunction = this.stringToPointerFunction;
					break;
				case CHAR:
					conversionFunction = this.stringToCharFunction;
					break;
				case STRING:
					conversionFunction = this.stringToStringDataFunction;
					break;
				default:
				}
				if (conversionFunction != null) {
					data = universe.apply(conversionFunction,
							Arrays.asList(format, currentString));
					state = primaryExecutor.assign(source, state,
							argumentValues[dataPointerIndex++], data);
					count++;
				}
				position = universe.add(position, universe.integer(1));
			}
			fileObject = universe.tupleWrite(fileObject, oneObject,
					fileContents);
			state = primaryExecutor.assign(source, state, filePointer,
					fileObject);
			fileStream = universe.tupleWrite(fileStream, twoObject, position);
			state = primaryExecutor.assign(source, state, argumentValues[0],
					fileStream);
			if (lhs != null) {
				SymbolicExpression countValue = universe.integer(count);

				state = primaryExecutor.assign(state, pid, lhs, countValue);
			}
		}
		return state;
	}

	/**
	 * Execute the function call for fprintf
	 * <code>int fprintf(FILE * restrict stream,
	 * const char * restrict format, ...)</code>.
	 * 
	 * @param source
	 *            The source code element of the function call.
	 * @param state
	 *            The state where the function call happens.
	 * @param pid
	 *            The ID of the process that this function call belongs to.
	 * @param lhs
	 *            The left hand side of the function call.
	 * @param arguments
	 *            The list of CIVL expressions for the arguments of the function
	 *            call.
	 * @param argumentValues
	 *            The list of symbolic expressions representing the value of the
	 *            arguments of the function call.
	 * @return
	 * @throws UnsatisfiablePathConditionException
	 */
	private State execute_fprintf(CIVLSource source, State state, int pid,
			LHSExpression lhs, Expression[] arguments,
			SymbolicExpression[] argumentValues)
			throws UnsatisfiablePathConditionException {
		SymbolicExpression fileStream;
		SymbolicExpression filePointer;
		Evaluation eval;
		SymbolicExpression fileObject;
		SymbolicExpression fileName;
		Pair<State, StringBuffer> stringResult;
		String fileNameString;
		StringBuffer stringOfSymbolicExpression;
		StringBuffer formatBuffer;
		ArrayList<StringBuffer> printedContents = new ArrayList<>();
		ArrayList<Integer> sIndexes = new ArrayList<>();
		Pattern pattern;
		Matcher matcher;
		int sCount = 2;
		Pair<State, StringBuffer> concreteString;

		eval = evaluator.dereference(arguments[0].getSource(), state,
				argumentValues[0]);
		fileStream = eval.value;
		state = eval.state;
		filePointer = universe.tupleRead(fileStream, zeroObject);
		eval = evaluator.dereference(arguments[0].getSource(), state,
				filePointer);
		fileObject = eval.value;
		state = eval.state;
		fileName = universe.tupleRead(fileObject, zeroObject);
		stringResult = this.getString(source, state, fileName);
		state = stringResult.left;
		fileNameString = stringResult.right.substring(0,
				stringResult.right.length() - 1);
		concreteString = this.getString(arguments[1].getSource(), state,
				argumentValues[1]);
		formatBuffer = concreteString.right;
		state = concreteString.left;
		pattern = Pattern
				.compile("((?<=[^%])|^)%[0-9]*[.]?[0-9|*]*[sdfoxegacpuxADEFGX]");
		matcher = pattern.matcher(formatBuffer);
		while (matcher.find()) {
			String formatSpecifier = matcher.group();
			if (formatSpecifier.compareTo("%s") == 0) {
				sIndexes.add(sCount);
			}
			sCount++;
		}
		for (int i = 2; i < argumentValues.length; i++) {
			SymbolicExpression argumentValue = argumentValues[i];
			CIVLType argumentType = arguments[i].getExpressionType();

			if (argumentType instanceof CIVLPointerType
					&& ((CIVLPointerType) argumentType).baseType().isCharType()
					&& argumentValue.operator() == SymbolicOperator.CONCRETE) {
				// also check format code is %s before doing this
				if (!sIndexes.contains(i)) {
					throw new CIVLSyntaxException("Array pointer unaccepted",
							arguments[i].getSource());
				}
				concreteString = this.getString(arguments[i].getSource(),
						state, argumentValue);
				stringOfSymbolicExpression = concreteString.right;
				state = concreteString.left;
				printedContents.add(stringOfSymbolicExpression);
			} else
				printedContents.add(new StringBuffer(argumentValue.toString()));
		}
		if (fileNameString.equals(STDOUT)) {
			this.printf(this.output, arguments[1].getSource(), formatBuffer,
					printedContents);
		} else if (fileNameString.equals(STDIN)) {
			// TODO: stdin
		} else if (fileNameString.equals(STDERR)) {
			this.printf(this.err, arguments[1].getSource(), formatBuffer,
					printedContents);
		}
		{ // updates the file
			SymbolicExpression fileContents = universe.tupleRead(fileObject,
					oneObject);
			List<Format> formats = this.splitFormat(arguments[1].getSource(),
					formatBuffer);
			int newContentCount = formats.size();
			int dataIndex = 2;

			for (int i = 0; i < newContentCount; i++) {
				Format currentFormat = formats.get(i);
				SymbolicExpression newStringExpression = null;
				ConversionType conversion = currentFormat.type;
				String formatString = currentFormat.string.toString();
				SymbolicConstant conversionFunction = null;

				switch (conversion) {
				case INT:
					conversionFunction = this.intToStringFunction;
					break;
				case DOUBLE:
					conversionFunction = this.doubleToStringFunction;
					break;
				case CHAR:
					conversionFunction = this.charToStringFunction;
					break;
				case STRING:
					conversionFunction = this.stringDataToStringFunction;
					break;
				case POINTER:
					conversionFunction = this.pointerToStringFunction;
					break;
				default:// VOID
					newStringExpression = universe
							.stringExpression(formatString
									.replaceAll("%%", "%"));
				}
				if (conversionFunction != null)
					newStringExpression = universe.apply(conversionFunction,
							Arrays.asList(
									universe.stringExpression(formatString),
									argumentValues[dataIndex++]));
				fileContents = universe.append(fileContents,
						newStringExpression);
			}
			fileObject = universe.tupleWrite(fileObject, oneObject,
					fileContents);
			state = primaryExecutor.assign(source, state, filePointer,
					fileObject);
		}
		return state;
	}

	/**
	 * Parses the format string, according to C11 standards. For example,
	 * <code>"This is process %d.\n"</code> will be parsed into a list of
	 * strings: <code>"This is process "</code>, <code>"%d"</code>,
	 * <code>".\n"</code>.<br>
	 * 
	 * In Paragraph 4, Section 7.21.6.1, C11 Standards:<br>
	 * Each conversion specification is introduced by the character %. After the
	 * %, the following appear in sequence:
	 * <ul>
	 * <li>Zero or more flags (in any order) that modify the meaning of the
	 * conversion specification.</li>
	 * <li>An optional minimum field width. If the converted value has fewer
	 * characters than the field width, it is padded with spaces (by default) on
	 * the left (or right, if the left adjustment flag, described later, has
	 * been given) to the field width. The field width takes the form of an
	 * asterisk * (described later) or a nonnegative decimal integer.</li>
	 * <li>An optional precision that gives the minimum number of digits to
	 * appear for the d, i, o, u, x, and X conversions, the number of digits to
	 * appear after the decimal-point character for a, A, e, E, f, and F
	 * conversions, the maximum number of significant digits for the g and G
	 * conversions, or the maximum number of bytes to be written for s
	 * conversions. The precision takes the form of a period (.) followed either
	 * by an asterisk * (described later) or by an optional decimal integer; if
	 * only the period is specified, the precision is taken as zero. If a
	 * precision appears with any other conversion specifier, the behavior is
	 * undefined.</li>
	 * <li>An optional length modifier that specifies the size of the argument.</li>
	 * <li>A conversion specifier character that specifies the type of
	 * conversion to be applied.</li>
	 * </ul>
	 * 
	 * @param source
	 *            The source code element of the format argument.
	 * @param formatBuffer
	 *            The string buffer containing the content of the format string.
	 * @return A list of string buffers by splitting the format by conversion
	 *         specifiers.
	 */
	private List<Format> splitFormat(CIVLSource source,
			StringBuffer formatBuffer) {
		int count = formatBuffer.length();
		List<Format> result = new ArrayList<>();
		StringBuffer stringBuffer = new StringBuffer();
		boolean inConversion = false;
		boolean hasFieldWidth = false;
		boolean hasPrecision = false;

		for (int i = 0; i < count; i++) {
			Character current = formatBuffer.charAt(i);
			Character code;
			ConversionType type = ConversionType.VOID;

			if (current.equals('%')) {
				code = formatBuffer.charAt(i + 1);

				if (code.equals('%')) {
					stringBuffer.append("%%");
					i = i + 1;
					continue;
				}
				if (stringBuffer.length() > 0) {
					if (stringBuffer.charAt(0) == '%'
							&& stringBuffer.charAt(1) != '%') {
						throw new CIVLSyntaxException("The format %"
								+ stringBuffer + " is not allowed in fprintf",
								source);
					}
					result.add(new Format(stringBuffer, type));
					stringBuffer = new StringBuffer();
				}
				inConversion = true;
				stringBuffer.append('%');
				current = formatBuffer.charAt(++i);
			}
			if (inConversion) {
				// field width
				if (current.equals('*')) {
					stringBuffer.append('*');
					current = formatBuffer.charAt(++i);
				} else if (numbers.contains(current)) {
					Character next = current;

					if (hasFieldWidth) {
						stringBuffer.append(next);
						throw new CIVLSyntaxException(
								"Duplicate field width in \"" + stringBuffer
										+ "\"...", source);
					}
					hasFieldWidth = true;
					while (numbers.contains(next)) {
						stringBuffer.append(next);
						next = formatBuffer.charAt(++i);
					}
					current = next;
				}
				// precision
				if (current.equals('.')) {
					Character next;

					next = formatBuffer.charAt(++i);
					stringBuffer.append('.');
					if (hasPrecision) {
						throw new CIVLSyntaxException(
								"Duplicate precision detected in \""
										+ stringBuffer + "\"...", source);
					}
					hasPrecision = true;
					if (next.equals('*')) {
						stringBuffer.append(next);
						next = formatBuffer.charAt(++i);
					} else {
						while (numbers.contains(next)) {
							stringBuffer.append(next);
							next = formatBuffer.charAt(++i);
						}
					}
					current = next;
				}
				// length modifier
				switch (current) {
				case 'h':
				case 'l':
					stringBuffer.append(current);
					if (i + 1 >= count)
						throw new CIVLSyntaxException("The format "
								+ stringBuffer + " is not allowed.", source);
					else {
						Character next = formatBuffer.charAt(i + 1);

						if (next.equals(current)) {
							i++;
							stringBuffer.append(next);
						}
						current = formatBuffer.charAt(++i);
					}
					break;
				case 'j':
				case 'z':
				case 't':
				case 'L':
					stringBuffer.append(current);
					current = formatBuffer.charAt(++i);
					break;
				default:
				}
				// conversion specifier
				switch (current) {
				case 'c':
				case 'p':
				case 'n':
					if (hasFieldWidth || hasPrecision) {
						throw new CIVLSyntaxException(
								"Invalid precision for the format \"%"
										+ current + "\"...", source);
					}
				default:
				}
				switch (current) {
				case 'c':
					type = ConversionType.CHAR;
					break;
				case 'p':
				case 'n':
					type = ConversionType.POINTER;
					break;
				case 'd':
				case 'i':
				case 'o':
				case 'u':
				case 'x':
				case 'X':
					type = ConversionType.INT;
					break;
				case 'a':
				case 'A':
				case 'e':
				case 'E':
				case 'f':
				case 'F':
				case 'g':
				case 'G':
					type = ConversionType.DOUBLE;
					break;
				case 's':
					type = ConversionType.STRING;
					break;
				default:
					stringBuffer.append(current);
					throw new CIVLSyntaxException("The format %" + stringBuffer
							+ " is not allowed in fprintf", source);
				}
				stringBuffer.append(current);
				result.add(new Format(stringBuffer, type));
				inConversion = false;
				hasFieldWidth = false;
				hasPrecision = false;
				stringBuffer = new StringBuffer();
			} else {
				stringBuffer.append(current);
			}
		}
		if (stringBuffer.length() > 0)
			result.add(new Format(stringBuffer, ConversionType.VOID));
		return result;
	}

	/**
	 * Prints to the standard output stream.
	 * 
	 * @param source
	 *            The source code information of the format argument.
	 * @param formatBuffer
	 *            The format string buffer.
	 * @param arguments
	 *            The list of arguments to be printed according to the format.
	 */
	private void printf(PrintStream printStream, CIVLSource source,
			StringBuffer formatBuffer, ArrayList<StringBuffer> arguments) {
		if (this.enablePrintf) {
			String format = formatBuffer.substring(0);

			format = format.replaceAll("%lf", "%s");
			format = format.replaceAll("%Lf", "%s");
			format = format
					.replaceAll(
							"((?<=[^%])|^)%[0-9]*[.]?[0-9|*]*[dfoxegacpuxADEFGX]",
							"%s");
			for (int i = 0; i < format.length(); i++) {
				if (format.charAt(i) == '%') {
					if (format.charAt(i + 1) == '%') {
						i++;
						continue;
					}
					if (format.charAt(i + 1) != 's')
						throw new CIVLSyntaxException("The format:%"
								+ format.charAt(i + 1)
								+ " is not allowed in printf", source);
				}
			}
			try {
				printStream.printf(format, arguments.toArray());

			} catch (Exception e) {
				throw new CIVLInternalException("unexpected error in printf",
						source);
			}
		}
	}

	/* ************************ Methods from Library *********************** */

	@Override
	public String name() {
		return "stdio";
	}

	/* ******************** Methods from LibraryExecutor ******************* */

	@Override
	public State execute(State state, int pid, CallOrSpawnStatement statement)
			throws UnsatisfiablePathConditionException {
		return executeWork(state, pid, (CallOrSpawnStatement) statement);
	}

	@Override
	public State initialize(State state) {
		// create abstract functions
		// create stdout, stdin
		// need to find variable called CIVL_files, but which process looks?
		// can make all these functions take an extra argument ($filesystem).
		// like malloc, can have two versions
		// or know which process is invoking and look up from there
		return state;
	}

	@Override
	public State wrapUp(State state) {
		return state;
	}

	enum ConversionType {
		INT, DOUBLE, CHAR, STRING, POINTER, VOID
	};

	private class Format {
		ConversionType type;
		StringBuffer string;

		Format(StringBuffer content, ConversionType conversion) {
			this.string = content;
			this.type = conversion;
		}

		@Override
		public String toString() {
			return this.string.toString();
		}
	}
}
