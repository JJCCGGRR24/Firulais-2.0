public class generator {

	public static void main(final String[] args) {
		for (int i = 1; i <= 15; i++)
			generator.a();
	}

	private static void a() {
		System.out.println(generator.r(3) + "" + generator.r(10) + "/0" + (generator.r(9) + 1) + "/" + "2108 " + (generator.r(13) + 10) + ":" + (generator.r(49) + 10));
	}
	private static int r(final int i) {
		final double d = Math.random();
		return (int) (d * i);
	}
}
