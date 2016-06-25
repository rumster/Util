package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.StaticFieldRef;

/**
 * Matches an assignment to a static field.
 * 
 * @author romanm
 */
public class CaseAssignStaticFieldRef extends CaseAssignFieldRef {
	public StaticFieldRef staticFieldRef;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (lhs instanceof StaticFieldRef) {
			staticFieldRef = (StaticFieldRef) lhs;
			return true;
		} else {
			return false;
		}
	}
}