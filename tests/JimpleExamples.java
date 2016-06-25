
public class JimpleExamples {
	static int s;
	static Object so;
	static Object to;
	int f;
	Object fo;

	public Object refExamples(Object myrefparam, Object myrefparam2, int q) {
		Object mythis = this;
		to = this;
		so = null;
		s = 5;

		JimpleExamples o = null;
		o = new JimpleExamples();
		o.f = 6;
		o.fo = null;
		o.fo = o;
		o.fo = so;

		Object u = o.fo;
		u = so;

		boolean b = o instanceof JimpleExamples;
		//if (b && q<7)
		int w = -q;
		float f = q;
		float k = -f;
		if (!b && w > 9 && k == 3.0)
			return refExamples(mythis, u, o.f);
		else
			return null;
	}

	public void arrayExamples(int s1) {
		Object[] a1 = new Object[s1];
		int[] a2 = new int[5];
		int q = a2.length;

		int[][][] a3 = new int[5][6][3];

		refExamples(a1, a3, q);
	}

	// public static int intExamples() {
	// int x = 0;
	// int y = x;
	// int z = hashCodeExample();
	// return y + z;
	// }

	// public static int hashCodeExample() {
	// Object a = new Object();
	// int h = a.hashCode();
	// return h;
	// }
}