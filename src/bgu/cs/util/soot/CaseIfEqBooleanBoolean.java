package bgu.cs.util.soot;

import soot.BooleanType;
import soot.Local;
import soot.Unit;
import soot.Value;
import soot.jimple.IntConstant;

/**
 * Matches a condition comparing two Boolean values.
 * 
 * @author romanm
 */
public class CaseIfEqBooleanBoolean extends CaseIfEq {
	public String op1Str;
	public String op2Str;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (op1.getType() instanceof BooleanType || op2.getType() instanceof BooleanType) {
			return true;
		} else {
			return false;
		}
	}

	public String valToStr(Value v) {
		op1Str = valToStr(op1);
		op2Str = valToStr(op2);
		if (v instanceof Local) {
			return v.toString();
		} else if (v instanceof IntConstant) {
			IntConstant c = (IntConstant) v;
			if (c.value == 1)
				return "tt";
			else if (c.value == 0)
				return "ff";
			else
				throw new Error("Encountered an unexpected constant: " + v);
		} else {
			throw new Error("Encountered an unexpected value type: " + v);
		}
	}
}