package bgu.cs.util.soot;

import java.util.List;

import soot.ArrayType;
import soot.Unit;
import soot.Value;
import soot.jimple.NewMultiArrayExpr;

/**
 * Matches an assignment of a new multi-dimension array to a local
 * reference-typed variable.
 * 
 * @author romanm
 */
public class CaseAssignLocal_NewMultiArrayExpr extends CaseAssignLocal_AnyNewExpr {
	public NewMultiArrayExpr newArrayExpr;
	public ArrayType elementType;
	public List<Value> sizes; // Check if this can be anything other than a
								// Local.

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (rhs instanceof NewMultiArrayExpr) {
			newArrayExpr = (NewMultiArrayExpr) rhs;
			elementType = newArrayExpr.getBaseType();
			sizes = newArrayExpr.getSizes();
			return true;
		} else {
			return false;
		}
	}
}