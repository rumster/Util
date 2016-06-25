package bgu.cs.util.soot;

import soot.Local;
import soot.Unit;
import soot.jimple.InstanceInvokeExpr;

/**
 * Matches an assignment from a call to an instance method.
 * 
 * @author romanm
 */
public class CaseAssignLocal_InvokeInstance extends CaseAssignLocal_Invoke {
	public InstanceInvokeExpr instanceExpr;
	public Local base;	

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (expr instanceof InstanceInvokeExpr) {
			instanceExpr = (InstanceInvokeExpr) expr;
			base = (Local) instanceExpr.getBase();
			return true;
		} else {
			return false;
		}
	}
}