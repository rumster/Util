package bgu.cs.util.soot;

import soot.RefType;
import soot.Unit;
import soot.jimple.AnyNewExpr;

/**
 * Matches an assignment to a local variable where the right-hand has a
 * reference type.
 * 
 * @author romanm
 */
public class CaseAssignLocal_RefExpr extends CaseAssignLocal {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return rhs.getType() instanceof RefType || rhs instanceof AnyNewExpr;
	}
}