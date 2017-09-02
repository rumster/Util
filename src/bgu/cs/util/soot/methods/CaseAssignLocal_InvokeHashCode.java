package bgu.cs.util.soot.methods;

import bgu.cs.util.soot.CaseAssignLocal_InvokeInstance;
import soot.Unit;

/**
 * Matches an assignment from a call to the {@link java.lang.Object}
 * <code>hashCode</code> method.
 * 
 * @author romanm
 */
public class CaseAssignLocal_InvokeHashCode extends CaseAssignLocal_InvokeInstance {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		return super.methodName.equals("hashCode") && super.args.isEmpty();
	}
}