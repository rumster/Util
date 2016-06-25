package bgu.cs.util.soot;

import soot.Unit;
import soot.jimple.IdentityStmt;
import soot.jimple.ParameterRef;

/**
 * Matches a Jimple {@link soot.jimple.IdentityStmt} where the right-hand side
 * is a method parameter.
 * 
 * @author romanm
 */
public class CaseIdentityStmtParameter extends CaseIdentityStmt {
	public IdentityStmt assign;
	public ParameterRef rhsParameter;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);

		if (rhs instanceof ParameterRef) {
			rhsParameter = (ParameterRef) rhs;
			assert rhsParameter.toString().startsWith("@parameter");
			return true;
		} else {
			return false;
		}
	}
}