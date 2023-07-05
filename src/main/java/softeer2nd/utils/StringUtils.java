package softeer2nd.utils;

public class StringUtils {

	public static final String NEWLINE = System.getProperty("line.separator");

	private StringUtils() {}

	public static String appendLine(final String input) {
		StringBuilder sb = new StringBuilder(input);
		sb.append(NEWLINE);
		return sb.toString();
	}
}
