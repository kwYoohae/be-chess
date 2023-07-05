package softeer2nd.chess.pieces;

import java.util.Objects;

public class Piece {

	public static final String WHITE_COLOR = "white";
	public static final String BLACK_COLOR = "black";
	public static final char WHITE_REPRESENTATION = 'p';
	public static final char BLACK_REPRESENTATION = 'P';
	public static final String WHITE_START_LOCATION = "2";
	public static final String BLACK_START_LOCATION = "7";

	private final String color;
	private final char representation;

	private Piece(String color, char representation) {
		this.color = color;
		this.representation = representation;
	}

	public static Piece createWhitePawn() {
		return new Piece(WHITE_COLOR, WHITE_REPRESENTATION);
	}

	public static Piece createBlackPawn() {
		return new Piece(BLACK_COLOR, BLACK_REPRESENTATION);
	}

	public String getColor() {
		return color;
	}

	public char getRepresentation() {
		return representation;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Piece piece = (Piece)o;
		return representation == piece.representation && Objects.equals(color, piece.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, representation);
	}
}
