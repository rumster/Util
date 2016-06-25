package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.IdentityStmt;
import soot.jimple.ThisRef;

/**
 * Matches a Jimple {@link soot.jimple.IdentityStmt} where the right-hand side
 * is the reference to `this`.
 * 
 * @author romanm
 */
public class CaseIdentityStmtThis extends CaseIdentityStmt {
	public IdentityStmt assign;
	public ThisRef rhsThis;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);

		if (rhs instanceof ThisRef) {
			rhsThis = (ThisRef) rhs;
			assert lhs.getName().equals("this");
			return true;
		} else {
			return false;
		}
	}
}