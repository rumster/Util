package bgu.cs.util.soot;

import soot.Unit;
import soot.Value;
import soot.jimple.ReturnStmt;

/**
 * Matches a Jimple {@link soot.jimple.ReturnStmt}.
 * 
 * @author romanm
 */
public class CaseReturnStmt extends JimpleCase {
	public ReturnStmt returnStmt;
	public Value op;

	@Override
	public boolean match(Unit stmt) {
		if (stmt instanceof ReturnStmt) {
			returnStmt = (ReturnStmt) stmt;
			op = returnStmt.getOp();
			return true;
		} else {
			return false;
		}
	}
}