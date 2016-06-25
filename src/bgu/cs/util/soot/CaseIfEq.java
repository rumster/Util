package bgu.cs.util.soot;

import soot.Unit;
import soot.Value;
import soot.jimple.ConditionExpr;
import soot.jimple.EqExpr;
import soot.jimple.NeExpr;

/**
 * Matches a condition comparing two values.
 * 
 * @author romanm
 */
public class CaseIfEq extends CaseIf {
	public Value op1;
	public Value op2;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (expr instanceof EqExpr || expr instanceof NeExpr) {
			ConditionExpr condExpr = (ConditionExpr)expr;
			op1 = condExpr.getOp1();
			op2 = condExpr.getOp2();

			negateLabels = expr instanceof NeExpr;
			return true;
		} else {
			return false;
		}
	}
}