package bgu.cs.util.soot;

import soot.Local;
import soot.RefType;
import soot.Unit;

/**
 * Matches an assignment to a local variable from another local variable of
 * primitive type.
 * 
 * @author romanm
 */
public class CaseAssignLocal_LocalNonRef extends CaseAssignLocal {
	public Local rhsLocal;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof Local && !(rhs.getType() instanceof RefType)) {
			rhsLocal = (Local) rhs;
			return true;
		} else {
			return false;
		}
	}
}