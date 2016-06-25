package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.ExitMonitorStmt;

/**
 * Matches a Jimple {@link soot.jimple.ExitMonitorStmt}.
 * 
 * @author romanm
 */
public class CaseExitMonitorStmt extends CaseMonitorStmt {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return stmt instanceof ExitMonitorStmt;
	}
}