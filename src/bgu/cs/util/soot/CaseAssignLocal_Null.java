package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.NullConstant;

/**
 * Matches an assignment of null to a local reference-typed variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_Null extends CaseAssignLocal_RefExpr {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return rhs instanceof NullConstant;
	}
}