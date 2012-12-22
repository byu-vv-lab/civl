package edu.udel.cis.vsl.civl.civlc.preproc.common;

import java.util.TreeMap;

import org.antlr.runtime.CharStream;

/**
 * A character stream that wraps another "original" character stream but removes
 * all occurrences of backslash followed by newline. This is to handle Phase 2
 * of the C translation process.
 * 
 * If a stream has n elements, numbered from 0 to n-1, the stream has n+1 cursor
 * positions, numbered 0 to n. Think of curson position i as just before element
 * i, and cursor position n as just after the last element.
 * 
 * A call to LA(1) should return the character just after the cursor. In other
 * words, if the cursor is at position p, then LA(1) should return element p. If
 * the cursor is at position n, LA(1) should return CharStream.EOF.
 * 
 * The cursor in the original stream will never rest just before a
 * backslash-newline. It will always be progressed after such a point. That
 * means a call to original.LA(1) should return the real next char.
 * 
 * @author Stephen F. Siegel, University of Delaware
 * 
 */
public class FilteredCharStream implements CharStream {

	private TreeMap<Integer, Integer> oldToNew = new TreeMap<Integer, Integer>();

	/**
	 * Current cursor position in the new (filtered) stream. It is always >= 0.
	 */
	private int cursor = 0;

	/**
	 * The original character stream that this one wraps.
	 */
	private CharStream original;

	/**
	 * The size of the new stream, or -1 if the size of the new stream is not
	 * yet known. It is not possible to predict the size of the new stream until
	 * the end of the stream has been reached because you don't know how many
	 * backslash-newlines are coming.
	 */
	private int size = -1;

	public FilteredCharStream(CharStream original) {
		this.original = original;
		// need to move original cursor forward past any initial
		// backslash-newlines to get to the first real element...
		progressOriginal();
	}

	/**
	 * Moves the cursor in the original stream forward past any
	 * backslash-newline sequences.
	 */
	private void progressOriginal() {
		while (original.LA(1) == '\\' && original.LA(2) == '\n') {
			original.consume(); // consumes the \\
			original.consume(); // consumes the \n
		}
	}

	/**
	 * Consume in original, but then look ahead and see if you consumed a
	 * backslash and a newline is coming up. If so, make an entry in the tables,
	 * consume twice more and repeat.
	 */
	@Override
	public void consume() {
		if (cursor == size) {
			return;
		} else {
			// original index of the real char about to be consumed
			int originalIndex = original.index();

			original.consume(); // consume the real character
			if (original.index() == originalIndex) { // end reached
				size = cursor;
				return;
			}
			cursor++;
			progressOriginal();
		}
	}

	/**
	 * For i=0, undefined.
	 * 
	 * For i>0: returns character at position p+i-1, p is current cursor value.
	 * 
	 * For i<0: return character at position p+i.
	 * 
	 * Various things are undefined if this goes beyond left or right boundary,
	 * but let original deal with this.
	 */
	@Override
	public int LA(int i) {
		if (i == 1 || i == 0)
			return original.LA(i);
		if (i >= 2) {
			int j = 1, k = 1, c;

			do {
				j++;
				k++;
				while ((c = original.LA(k)) == '\\'
						&& original.LA(k + 1) == '\n')
					k += 2;
			} while (j < i);
			return c;
		} else { // i<0. go backwards...
			int j = -1, k = 0, c;

			do {
				j--;
				k--;
				while ((c = original.LA(k)) == '\n'
						&& original.LA(k - 1) == '\\')
					k -= 2;
			} while (j >= i);
			return c;
		}
	}

	/**
	 * This does not change the index or state, but records something about the
	 * state and returns a reference to that record as an int (the "marker"). A
	 * subsequent call to rewind(marker) returns the stream to the recorded
	 * state. We need to record the index correspondence in order to re-set our
	 * index when rewind is called.
	 */
	@Override
	public int mark() {
		oldToNew.put(original.index(), cursor);
		return original.mark();
	}

	/**
	 * Returns the current cursor position, index. This is initially 0. It is
	 * the index of the character that will be returned by invoking LA(1).
	 */
	@Override
	public int index() {
		return cursor;
	}

	/**
	 * Just invoke on original and reset index.
	 */
	@Override
	public void rewind(int marker) {
		original.rewind(marker);
		cursor = oldToNew.get(original.index());
	}

	/**
	 * Just invoke on original and reset index.
	 */
	@Override
	public void rewind() {
		original.rewind();
		cursor = oldToNew.get(original.index());
	}

	/**
	 * Just invoke on original.
	 */
	@Override
	public void release(int marker) {
		original.release(marker);
		// maybe I can remove something from the map now?
	}

	/**
	 * Set current cursor position to given position.
	 * 
	 * Need to translate index from new to old. You can do this if given index
	 * is less than or equal to current cursor position, but if it is greater,
	 * you need to consume ahead, because you don't know where the
	 * backslash-newlines are going to be.
	 */
	@Override
	public void seek(int index) {
		int delta = index - this.cursor;

		assert index >= 0;
		if (delta == 0)
			return;
		if (delta > 0) {
			while (delta > 0) {
				consume();
				delta--;
			}
		} else { // delta < 0
			// use seek
			int k = original.index();

			while (delta < 0) {
				k--;
				this.cursor--;
				original.seek(k);
				while (original.LA(-1) == '\\' && original.LA(1) == '\n') {
					k -= 2;
					// It should not be possible for k to be negative...
					assert k >= 0;
					original.seek(k);
				}
				delta++;
			}
		}
	}

	/**
	 * No way to implement this without looking to the end, because you don't
	 * know where the backslash-newlines are.
	 * 
	 * It SEEMS like this should return the number of characters. That is what
	 * the ANTLRStringStream does.
	 * 
	 */
	@Override
	public int size() {
		if (size >= 0)
			return size;
		// seek until you find EOF...
		int j = 0, k = 1;

		while (true) {
			int c = original.LA(k);

			if (c == CharStream.EOF)
				break;
			while (c == '\\' && original.LA(k + 1) == '\n') {
				k += 2;
				c = original.LA(k);
			}
			j++;
			k++;
		}
		// when LA(1) first returns EOF then index is size
		size = cursor + j;
		return size;
	}

	@Override
	public String getSourceName() {
		return original.getSourceName();
	}

	/**
	 * Start and stop are absolute indices; this method does not depend on the
	 * current cursor position. Just convert from new to old but skip over the
	 * backslash-newlines!
	 */
	@Override
	public String substring(int start, int stop) {
		int holdIndex = cursor;
		int count = stop - start + 1;
		char[] characters = new char[count];

		seek(start);
		for (int i = start, j = 0; i <= stop; i++, j++) {
			seek(i);
			characters[j] = (char) LA(1);
		}
		seek(holdIndex);
		return new String(characters);
	}

	/**
	 * Until I can figure out the difference, same as LA.
	 */
	@Override
	public int LT(int i) {
		return LA(i);
	}

	/**
	 * Just invoke on original
	 */
	@Override
	public int getLine() {
		return original.getLine();
	}

	/** Just invoke on original */
	@Override
	public void setLine(int line) {
		original.setLine(line);
	}

	/**
	 * Sets the char-position-in-line associated to the current character, i.e.,
	 * the one just after the cursor.
	 * 
	 * Not exactly sure how this is used or what the contract should be in light
	 * of the removal of backslash-newlines.
	 * 
	 * Same as in original.
	 */
	@Override
	public void setCharPositionInLine(int pos) {
		original.setCharPositionInLine(pos);
	}

	/**
	 * Same as in original.
	 */
	@Override
	public int getCharPositionInLine() {
		return original.getCharPositionInLine();
	}

}
