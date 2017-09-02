package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.EnterMonitorStmt;

/**
 * Matches a Jimple {@link soot.jimple.EnterMonitorStmt}.
 * 
 * @author romanm
 */
public class CaseEnterMonitorStmt extends CaseMonitorStmt {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return stmt instanceof EnterMonitorStmt;
	}
}