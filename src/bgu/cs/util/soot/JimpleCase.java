package bgu.cs.util.soot;

import soot.Unit;
import bgu.cs.util.Matcher;

/**
 * The base class of Cases classes for Jimple statements.
 * 
 * @author romanm
 *
 */
public class JimpleCase extends Matcher.Case<Unit> {
	public String preLabel;
	public String postLabel;
	public String type;
	
	public Unit stmt;	

	@Override
	public boolean match(Unit stmt) {
		this.stmt = stmt;		
		return true;
	}	
}