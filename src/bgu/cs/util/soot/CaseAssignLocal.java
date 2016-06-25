package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;

/**
 * Matches an assignment to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal extends CaseAssign {
	public Local lhsLocal;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (lhs instanceof Local) {
			lhsLocal = (Local) lhs;
			return true;
		} else {
			return false;
		}
	}
}