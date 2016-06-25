package bgu.cs.util.soot;

import soot.BooleanType;
import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.InstanceOfExpr;

/**
 * Matches an assignment of an array length to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_InstanceofExpr extends CaseAssignLocal {
	public InstanceOfExpr instanceOfExpr;
	public Type checkType;
	public Value op;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof InstanceOfExpr) {
			instanceOfExpr = (InstanceOfExpr) rhs;
			checkType = instanceOfExpr.getCheckType();
			op = instanceOfExpr.getOp();
			assert lhsLocal.getType() instanceof BooleanType;
			return true;
		} else {
			return false;
		}
	}
}