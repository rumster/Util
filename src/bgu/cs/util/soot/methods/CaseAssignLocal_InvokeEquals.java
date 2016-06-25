package bgu.cs.util.soot.methods;

import bgu.cs.util.soot.CaseAssignLocal_InvokeInstance;
import soot.RefType;
import soot.Unit;

/**
 * Matches an assignment from a call to the {@link java.lang.Object.equals}
 * method.
 * 
 * @author romanm
 */
public class CaseAssignLocal_InvokeEquals extends CaseAssignLocal_InvokeInstance {
	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (super.methodName.equals("equals") && super.args.size() == 1
				&& super.args.get(0).getType() instanceof RefType) {
			RefType t = (RefType) super.args.get(0).getType();
			return t.getClassName().equals("Object");
		} else {
			return false;
		}
	}
}