package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;
import soot.jimple.StaticFieldRef;

/**
 * Matches an assignment of a static field to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocalRef_StaticFieldRef extends CaseAssignLocal {
	public StaticFieldRef staticFieldRef;
	public Local base;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof StaticFieldRef) {
			staticFieldRef = (StaticFieldRef) rhs;
			return true;
		} else {
			return false;
		}
	}
}