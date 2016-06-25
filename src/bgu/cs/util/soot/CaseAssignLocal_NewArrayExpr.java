package bgu.cs.util.soot;

import soot.Type;
import soot.Unit;
import soot.Value;
import soot.jimple.NewArrayExpr;

/**
 * Matches an assignment of a new array to a local reference-typed variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_NewArrayExpr extends CaseAssignLocal_AnyNewExpr {
	public NewArrayExpr newArrayExpr;
	public Type elementType;
	public Value size; // Check if this can be anything other than a Local.

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof NewArrayExpr) {
			newArrayExpr = (NewArrayExpr) rhs;
			elementType = newArrayExpr.getBaseType();
			size = newArrayExpr.getSize();
			return true;
		} else {
			return false;
		}
	}
}