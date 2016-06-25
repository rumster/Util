package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.ReturnVoidStmt;

/**
 * Matches a Jimple {@link soot.jimple.ReturnVoidStmt}.
 * 
 * @author romanm
 */
public class CaseReturnVoidStmt extends JimpleCase {
	public ReturnVoidStmt returnStmt;

	@Override
	public boolean match(Unit stmt) {
		if (stmt instanceof ReturnVoidStmt) {
			returnStmt = (ReturnVoidStmt) stmt;
			return true;
		} else {
			return false;
		}
	}
}