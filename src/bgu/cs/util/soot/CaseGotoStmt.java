package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.GotoStmt;

/**
 * Matches a Jimple {@link soot.jimple.AssignStmt}.
 * 
 * @author romanm
 */
public class CaseGotoStmt extends JimpleCase {
	public GotoStmt gotoStmt;

	@Override
	public boolean match(Unit stmt) {
		if (stmt instanceof GotoStmt) {
			gotoStmt = (GotoStmt) stmt;
			return true;
		} else {
			return false;
		}
	}
}