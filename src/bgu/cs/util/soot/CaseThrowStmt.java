package bgu.cs.util.soot;

import soot.Unit;
import soot.Value;
import soot.jimple.ThrowStmt;

/**
 * Matches {@link soot.jimple.ThrowStmt}.
 * 
 * @author romanm
 */
public class CaseThrowStmt extends JimpleCase {
	public ThrowStmt throwStmt;
	public Value op;

	@Override
	public boolean match(Unit stmt) {
		if (stmt instanceof ThrowStmt) {
			throwStmt = (ThrowStmt) stmt;
			op = throwStmt.getOp();
			return true;
		} else {
			return false;
		}
	}
}