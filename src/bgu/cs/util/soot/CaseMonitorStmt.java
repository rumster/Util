package bgu.cs.util.soot;

import soot.RefType;
import soot.Unit;
import soot.Value;
import soot.jimple.MonitorStmt;

/**
 * Matches a Jimple {@link soot.jimple.MonitorStmt}.
 * 
 * @author romanm
 */
public class CaseMonitorStmt extends JimpleCase {
	public MonitorStmt monitorStmt;
	public Value op;

	@Override
	public boolean match(Unit stmt) {
		if (stmt instanceof MonitorStmt) {
			monitorStmt = (MonitorStmt) stmt;
			op = monitorStmt.getOp();
			assert op.getType() instanceof RefType;
			return true;
		} else {
			return false;
		}
	}
}