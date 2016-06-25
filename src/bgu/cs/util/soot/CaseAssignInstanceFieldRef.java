package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;
import soot.jimple.InstanceFieldRef;

/**
 * Matches an assignment to an instance field.
 * 
 * @author romanm
 */
public class CaseAssignInstanceFieldRef extends CaseAssignFieldRef {
	public InstanceFieldRef instanceFieldRef;
	public Local base;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (lhs instanceof InstanceFieldRef) {
			instanceFieldRef = (InstanceFieldRef) lhs;
			base = (Local) instanceFieldRef.getBase();			
			return true;
		} else {
			return false;
		}
	}
}