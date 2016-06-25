package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;
import soot.Value;
import soot.jimple.IdentityStmt;

/**
 * Matches a Jimple {@link soot.jimple.IdentityStmt}.
 * 
 * @author romanm
 */
public class CaseIdentityStmt extends JimpleCase {
	public IdentityStmt assign;
	public Local lhs;
	public Value rhs;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (stmt instanceof IdentityStmt) {
			assign = (IdentityStmt) stmt;
			lhs = (Local) assign.getLeftOp();
			rhs = assign.getRightOp();
			return true;
		} else {
			return false;
		}
	}
}