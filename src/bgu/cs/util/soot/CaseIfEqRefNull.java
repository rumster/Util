package bgu.cs.util.soot;

import soot.RefType;
import soot.Unit;
import soot.jimple.NullConstant;

/**
 * 
 * @author borisd
 */
public class CaseIfEqRefNull extends CaseIfEq {
	public String opStr;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (op1.getType() instanceof RefType && op2 instanceof NullConstant ) {
			opStr = op1.toString();
			return true;
		}
		else if (op2.getType() instanceof RefType && op1 instanceof NullConstant ) {
			opStr = op2.toString();
			return true;
		}
		else {
			return false;
		}
	}
}