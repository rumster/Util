package bgu.cs.util.soot;

import soot.Unit;
import soot.Value;
import soot.jimple.BinopExpr;

/**
 * Matches an assignment of a binary operator expression to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_BinopExpr extends CaseAssignLocal {
	public Value op1;
	public Value op2;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof BinopExpr) {
			BinopExpr expr = (BinopExpr) rhs;
			op1 = expr.getOp1();
			op2 = expr.getOp2();
			return true;
		} else {
			return false;
		}
	}
}