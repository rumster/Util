package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;
import soot.jimple.InstanceFieldRef;

/**
 * Matches an assignment of an instance field to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_InstanceFieldRef extends CaseAssignLocal_RefExpr {
	public InstanceFieldRef instanceFieldRef;
	public Local base;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof InstanceFieldRef) {
			instanceFieldRef = (InstanceFieldRef) rhs;
			base = (Local) instanceFieldRef.getBase();
			return true;
		} else {
			return false;
		}
	}
}