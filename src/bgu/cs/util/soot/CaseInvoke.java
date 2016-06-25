package bgu.cs.util.soot;

import java.util.List;

import soot.Unit;
import soot.Value;
import soot.jimple.InvokeExpr;
import soot.jimple.InvokeStmt;

/**
 * Matches a Jimple {@link soot.jimple.InvokeStmt}.
 * 
 * @author romanm
 */
public class CaseInvoke extends JimpleCase {
	public InvokeStmt invoke;
	public InvokeExpr expr;
	public String methodName;
	public List<Value> args;

	@Override
	public boolean match(Unit stmt) {
		if (stmt instanceof InvokeStmt) {
			invoke = (InvokeStmt) stmt;
			expr = invoke.getInvokeExpr();
			methodName = expr.getMethod().getName();
			args = expr.getArgs();
			return true;
		} else {
			return false;
		}
	}
}