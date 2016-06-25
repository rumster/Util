package bgu.cs.util.soot;

import soot.Unit;
import soot.Value;
import soot.jimple.AssignStmt;

/**
 * Matches a Jimple {@link soot.jimple.AssignStmt}.
 * 
 * @author romanm
 */
public class CaseAssign extends JimpleCase {
	public AssignStmt assign;
	public Value lhs;
	public Value rhs;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (stmt instanceof AssignStmt) {
			assign = (AssignStmt) stmt;
			lhs = assign.getLeftOp();
			rhs = assign.getRightOp();
			return true;
		} else {
			return false;
		}
	}
}