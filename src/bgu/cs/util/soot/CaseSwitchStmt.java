package bgu.cs.util.soot;

import java.util.List;

import soot.Unit;
import soot.Value;
import soot.jimple.SwitchStmt;

/**
 * Matches a Jimple {@link soot.jimple.TableSwitchStmt}.
 * 
 * @author romanm
 */
public class CaseSwitchStmt extends JimpleCase {
	public SwitchStmt switchStmt;
	public Unit defaultTarget;
	public Value key;
	public List<Unit> targets;

	@Override
	public boolean match(Unit stmt) {
		if (stmt instanceof SwitchStmt) {
			switchStmt = (SwitchStmt) stmt;
			defaultTarget = switchStmt.getDefaultTarget();
			key = switchStmt.getKey();
			targets = switchStmt.getTargets();
			return true;
		} else {
			return false;
		}
	}
}