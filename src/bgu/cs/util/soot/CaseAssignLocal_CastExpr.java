package bgu.cs.util.soot;

import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.CastExpr;

/**
 * Matches an assignment of a cast expression to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_CastExpr extends CaseAssignLocal {
	public Value op;
	public Type type;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof CastExpr) {
			CastExpr expr = (CastExpr) rhs;
			op = expr.getOp();
			type = expr.getCastType();
			return true;
		} else {
			return false;
		}
	}
}