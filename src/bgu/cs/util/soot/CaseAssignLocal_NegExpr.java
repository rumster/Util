package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;
import soot.jimple.NegExpr;

/**
 * Matches an assignment of a negation expression to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_NegExpr extends CaseAssignLocal {
	public Local op;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof NegExpr) {
			NegExpr expr = (NegExpr) rhs;
			op = (Local) expr.getOp();
			return true;
		} else {
			return false;
		}
	}
}