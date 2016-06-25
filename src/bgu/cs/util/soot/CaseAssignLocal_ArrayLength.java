package bgu.cs.util.soot;

import soot.IntegerType;
import soot.Unit;
import soot.jimple.LengthExpr;

/**
 * Matches an assignment of an array length to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_ArrayLength extends CaseAssignLocal {
	public LengthExpr lengthExpr;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof LengthExpr) {
			lengthExpr = (LengthExpr) rhs;
			assert lhsLocal.getType() instanceof IntegerType;
			return true;
		} else {
			return false;
		}
	}
}