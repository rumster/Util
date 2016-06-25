package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.AnyNewExpr;

/**
 * Matches an assignment of any expression that alloctes memory to a local
 * reference-typed variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_AnyNewExpr extends CaseAssignLocal_RefExpr {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return rhs instanceof AnyNewExpr;
	}
}