package bgu.cs.util.soot;

import soot.Unit;
import soot.Value;
import soot.jimple.ConditionExpr;
import soot.jimple.IfStmt;

/**
 * Matches a Jimple {@link soot.jimple.IfStmt}.
 * 
 * @author romanm
 */
public class CaseIf extends JimpleCase {
	public ConditionExpr expr;
	public Value op1;
	public Value op2;
	public String symbol;

	public String trueLabel;
	public String falseLabel;
	public Boolean negateLabels;

	@Override
	public boolean match(Unit stmt) {
		super.match(stmt);
		if (stmt instanceof IfStmt) {
			IfStmt ifStmt = (IfStmt) stmt;
			expr = (ConditionExpr) ifStmt.getCondition();
			op1 = expr.getOp1();
			op2 = expr.getOp2();
			symbol = expr.getSymbol();
			return true;
		} else {
			return false;
		}
	}

	public void SetLabels(String trueLabel, String falseLabel) {
		if (negateLabels) {
			this.trueLabel = trueLabel;
			this.falseLabel = falseLabel;
		} else {
			this.trueLabel = falseLabel;
			this.falseLabel = trueLabel;
		}
	}
}