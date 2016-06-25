package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.TableSwitchStmt;

/**
 * Matches a Jimple {@link soot.jimple.TableSwitchStmt}.
 * 
 * @author romanm
 */
public class CaseTableSwitchStmt extends CaseSwitchStmt {
	public TableSwitchStmt tableSwitchStmt;
	public int lowIndex;
	public int highIndex;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (stmt instanceof TableSwitchStmt) {
			tableSwitchStmt = (TableSwitchStmt) stmt;
			lowIndex = tableSwitchStmt.getLowIndex();
			highIndex = tableSwitchStmt.getHighIndex();
			return true;
		} else {
			return false;
		}
	}
}