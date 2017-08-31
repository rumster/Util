package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;

public class CaseAssignInstanceFieldRef_Local extends CaseAssignInstanceFieldRef {
	public Local rhs;

	@Override
	public boolean match(Unit stmt) {
		if (super.match(stmt)) {
			return rhs instanceof Local;
		}
		return false;
	}
}