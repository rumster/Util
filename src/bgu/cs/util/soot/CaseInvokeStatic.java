package bgu.cs.util.soot;

import soot.Type;
import soot.Unit;
import soot.jimple.StaticInvokeExpr;

/**
 * Matches a Jimple {@link soot.jimple.InvokeStmt} for a static method.
 * 
 * @author romanm
 */
public class CaseInvokeStatic extends CaseInvoke {
	public StaticInvokeExpr staticExpr;
	public Type type;

	@Override
	public boolean match(Unit stmt) {
		if (expr instanceof StaticInvokeExpr) {
			staticExpr = (StaticInvokeExpr) expr;
			staticExpr.getType();
			return true;
		} else {
			return false;
		}
	}
}