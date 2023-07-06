package softeer2nd.utils;

public class StringUtils {

	public static final String NEWLINE = System.getProperty("line.separator");

	private StringUtils() {}

	public static String appendNewLine(final String input) {
		return input + NEWLINE;
	}
}
