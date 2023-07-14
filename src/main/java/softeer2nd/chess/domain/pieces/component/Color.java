package softeer2nd.chess.domain.pieces.component;

public enum Color {
	WHITE("White"),
	BLACK("Black"),
	NOCOLOR("No Color");

	private final String name;

	Color(final String name) {
		this.name = name;
	}

	public static Color getOppositeColor(Color color) {
		return color == WHITE ? BLACK : WHITE;
	}
	public String getName() {
		return name;
	}
}
