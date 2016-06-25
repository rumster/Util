package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;

/**
 * Matches an assignment of a local reference variable to another local
 * reference variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_LocalRef extends CaseAssignLocal_RefExpr {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return rhs instanceof Local;
	}
}