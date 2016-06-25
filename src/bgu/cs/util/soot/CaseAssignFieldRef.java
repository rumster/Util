package bgu.cs.util.soot;

import soot.Local;
import soot.SootField;
import soot.Unit;
import soot.jimple.FieldRef;

/**
 * Matches an assignment to a field.
 * 
 * @author romanm
 */
public class CaseAssignFieldRef extends CaseAssign {
	public FieldRef fieldRef;
	public SootField field;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (lhs instanceof FieldRef) {
			fieldRef = (FieldRef) lhs;
			rhs = (Local) super.rhs;
			field = fieldRef.getField();
			return true;
		} else {
			return false;
		}
	}
}