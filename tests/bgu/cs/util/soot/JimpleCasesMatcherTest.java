package bgu.cs.util.soot;

import java.util.List;
import java.util.Map;

import bgu.cs.util.Matcher;
import bgu.cs.util.Matcher.Case;
import bgu.cs.util.StringUtils;
import soot.Body;
import soot.BodyTransformer;
import soot.PackManager;
import soot.Transform;
import soot.Unit;

/**
 * Tests the {@link bgu.cs.util.Matcher.Case} classes under bgu.cs.util.soot
 * against a given source file.
 * 
 * @author romanm
 */
public class JimpleCasesMatcherTest extends BodyTransformer {
	/**
	 * A list of arguments passed to Soot.
	 */
	public static final String DEFAULT_SOOT_ARGS = "-cp . -pp -f jimple -p jb use-original-names -p jb.ls enabled:false -p jb.ls enabled:false -keep-line-number -print-tags AEBenchmarks";

	static Matcher<Unit> jimpleMatcher = new Matcher<>();

	static {
		jimpleMatcher.addAllCasesFromPkg(bgu.cs.util.soot.JimpleCase.class.getPackage());
	}

	public static void main(String[] args) {
		String[] sootArgs = getArgs(args);
		String phaseName = "jtp.matcher";
		PackManager.v().getPack("jtp").add(new Transform(phaseName, new JimpleCasesMatcherTest()));
		soot.Main.main(sootArgs);
	}

	@Override
	protected void internalTransform(Body b, String phaseName, @SuppressWarnings("rawtypes") Map options) {
		System.out.println(String.format("\nTransforming %s...", b.getMethod().getName()));

		for (Unit u : b.getUnits()) {
			Case<Unit> matchedCase = jimpleMatcher.match(u);
			if (matchedCase != null) {
				System.out.println(String.format("%s -> %s", u, matchedCase.getClass().getSimpleName()));
			} else {
				System.out.println(String.format("%s -> ? (unmatched)", u));
				// throw new Error("Unable to match " + u);
			}
		}
	}

	protected static String[] getArgs(String[] args) {
		if (args.length == 1) {
			List<String> argsAsList = StringUtils.breakString(DEFAULT_SOOT_ARGS);
			argsAsList.add(args[0]);
			String[] sootArgs = argsAsList.toArray(new String[0]);
			return sootArgs;
		} else {
			return args;
		}
	}
}