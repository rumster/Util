package bgu.cs.util.soot;

import java.util.List;

import soot.Unit;
import soot.jimple.IntConstant;
import soot.jimple.LookupSwitchStmt;

/**
 * Matches a Jimple {@link soot.jimple.TableSwitchStmt}.
 * 
 * @author romanm
 */
public class CaseLookupSwitchStmt extends CaseSwitchStmt {
	public LookupSwitchStmt lookupSwitchStmt;

	public List<IntConstant> lookupValues;
	public int targetCount;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (stmt instanceof LookupSwitchStmt) {
			lookupSwitchStmt = (LookupSwitchStmt) stmt;
			lookupValues = lookupSwitchStmt.getLookupValues();
			targetCount = lookupSwitchStmt.getTargetCount();
			return true;
		} else {
			return false;
		}
	}
}