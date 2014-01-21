package edu.udel.cis.vsl.civl.model.common.statement;

import java.util.ArrayList;

import edu.udel.cis.vsl.civl.model.IF.CIVLSource;
import edu.udel.cis.vsl.civl.model.IF.expression.ConditionalExpression;
import edu.udel.cis.vsl.civl.model.IF.expression.Expression;
import edu.udel.cis.vsl.civl.model.IF.expression.LHSExpression;
import edu.udel.cis.vsl.civl.model.IF.location.Location;
import edu.udel.cis.vsl.civl.model.IF.statement.MPISendStatement;
import edu.udel.cis.vsl.civl.model.IF.statement.Statement;

/**
 * * An MPI standard-mode blocking send statement. Syntax:
 * 
 * <pre>
 * int MPI_Send(const void* buf, int count, MPI_Datatype datatype,
 *              int dest, int tag, MPI_Comm comm)
 * </pre>
 * 
 * Note that there is a return value, which is used to return an error code.
 * Under normal circumstances it returns 0.
 * 
 * TODO: complete java-docs
 * 
 * @author ziqingluo
 * 
 */
public class CommonMPISendStatement extends CommonStatement implements
		MPISendStatement {

	ArrayList<Expression> arguments;

	public CommonMPISendStatement(CIVLSource civlsource, Location source,
			ArrayList<Expression> arguments) {
		super(civlsource, source);
		this.arguments = new ArrayList<Expression>(arguments);

	}

	@Override
	public Expression getBuffer() {
		// TODO Auto-generated method stub
		return this.arguments.get(0);
	}

	@Override
	public Expression getCount() {
		// TODO Auto-generated method stub
		return this.arguments.get(1);
	}

	@Override
	public Expression getDatatype() {
		// TODO Auto-generated method stub
		return this.arguments.get(2);
	}

	@Override
	public Expression getDestination() {
		// TODO Auto-generated method stub
		return this.arguments.get(3);
	}

	@Override
	public Expression getTag() {
		// TODO Auto-generated method stub
		return this.arguments.get(4);
	}

	@Override
	public Expression getCommunicator() {
		// TODO Auto-generated method stub
		return this.arguments.get(5);
	}

	@Override
	public LHSExpression getLeftHandSize() {
		// TODO Auto-generated method stub
		return (LHSExpression) this.arguments.get(6);
	}
	
	public String toString(){
		if(this.getLeftHandSize() == null) {
		    return "MPI_Send(" + this.arguments.get(0) + ", " + this.arguments.get(1) +
				", " + this.arguments.get(2) + ", " + this.arguments.get(3) + 
				", " +this.arguments.get(4) + ", " + this.arguments.get(5) +
				")";
		    }
		else {
			return  this.arguments.get(6) +
			   " = MPI_Send(" + this.arguments.get(0) + ", " + this.arguments.get(1) +
			   ", " + this.arguments.get(2) + ", " + this.arguments.get(3) + 
			   ", " +this.arguments.get(4) + ", " + this.arguments.get(5) +
			   ")";
		}
	}

	@Override
	public Statement replaceWith(ConditionalExpression oldExpression,
			Expression newExpression) {
		// TODO Auto-generated method stub
		Expression newGuard = this.guardReplaceWith(oldExpression,
				newExpression);
		CommonMPISendStatement newStatement = null;

		if (newGuard != null) {
			newStatement = new CommonMPISendStatement(this.getSource(),
					this.source(), this.arguments);
			newStatement.setGuard(newGuard);
		} else {
			ArrayList<Expression> newArgs = new ArrayList<Expression>();
			int number = this.arguments.size();
			Expression newArg;
			boolean hasNewArg = false;

			for (int i = 0; i < number; i++) {
				if (hasNewArg)
					newArgs.add(this.arguments.get(i));
				else {
					newArg = this.arguments.get(i).replaceWith(oldExpression,
							newExpression);
					if (newArg != null) {
						newArgs.add(newArg);
						hasNewArg = true;
					} else
						newArgs.add(this.arguments.get(i));
				}
			}
			if (hasNewArg) {
				newStatement = new CommonMPISendStatement(this.getSource(),
						this.source(), this.arguments);
				newStatement.setGuard(newGuard);
			}
		}

		return newStatement;
	}

}
