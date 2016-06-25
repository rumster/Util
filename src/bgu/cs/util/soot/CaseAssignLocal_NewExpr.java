package bgu.cs.util.soot;

import soot.Type;
import soot.Unit;
import soot.jimple.NewExpr;

/**
 * Matches an assignment of new object to a local reference-typed variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_NewExpr extends CaseAssignLocal_AnyNewExpr {
	public NewExpr newExpr;
	public Type baseType;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof NewExpr) {
			newExpr = (NewExpr) rhs;
			baseType = newExpr.getBaseType();
			return true;
		} else {
			return false;
		}
	}
}