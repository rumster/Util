package bgu.cs.util.soot;

import java.util.List;

import soot.Unit;
import soot.Value;
import soot.jimple.InvokeExpr;

/**
 * Matches an assignment of a method invocation to a local variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_Invoke extends CaseAssignLocal {
	public InvokeExpr expr;
	public String methodName;
	public List<Value> args;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof InvokeExpr) {
			expr = (InvokeExpr) rhs;
			methodName = expr.getMethod().getName();
			args = expr.getArgs();
			return true;
		} else {
			return false;
		}

	}
}