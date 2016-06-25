package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.NopStmt;

/**
 * Matches a Jimple {@link soot.jimple.NopStmt}.
 * 
 * @author romanm
 */
public class CaseNopStmt extends JimpleCase {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return stmt instanceof NopStmt;
	}
}