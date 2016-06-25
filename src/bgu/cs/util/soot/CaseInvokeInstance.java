package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;
import soot.jimple.InstanceInvokeExpr;

/**
 * Matches a Jimple {@link soot.jimple.InvokeStmt} on an instance (object).
 * 
 * @author romanm
 */
public class CaseInvokeInstance extends CaseInvoke {
	public InstanceInvokeExpr instanceExpr;
	public Local base;

	@Override
	public boolean match(Unit stmt) {
		if (expr instanceof InstanceInvokeExpr) {
			instanceExpr = (InstanceInvokeExpr) expr;
			base = (Local) instanceExpr.getBase();			
			return true;
		} else {
			return false;
		}
	}
}