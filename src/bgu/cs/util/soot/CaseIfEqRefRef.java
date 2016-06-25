package bgu.cs.util.soot;

import soot.RefType;
import soot.Unit;

/**
 * 
 * @author borisd
 */
public class CaseIfEqRefRef extends CaseIfEq {
	public String op1Str;
	public String op2Str;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (op1.getType() instanceof RefType && op2.getType() instanceof RefType) {
			op1Str = op1.toString();
			op2Str = op2.toString();
			return true;
		} else {
			return false;
		}
	}
}