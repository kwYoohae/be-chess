package softeer2nd.chess.pieces;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Objects;

public class Piece {

	public static final String WHITE_COLOR = "white";
	public static final String BLACK_COLOR = "black";
	public static final char WHITE_REPRESENTATION = 'p';
	public static final char BLACK_REPRESENTATION = 'P';
	public static final String WHITE_START_LOCATION = "2";
	public static final String BLACK_START_LOCATION = "7";

	private final String color;
	private char representation;

	public Piece() {
		this(WHITE_COLOR);
	}

	public Piece(final String color) {
		this.color = color;
		setRepresentation(color);
	}

	public String getColor() {
		return color;
	}

	public char getRepresentation() {
		return representation;
	}

	private void setRepresentation(final String color) {
		if (Objects.equals(color, WHITE_COLOR)) {
			this.representation = WHITE_REPRESENTATION;
			return;
		}

		if (Objects.equals(color, BLACK_COLOR)) {
			this.representation = BLACK_REPRESENTATION;
			return;
		}
		throw new IllegalArgumentException(EXCEPTION_MESSAGE_CHESS_COLOR_MUST_BLACK_OR_WHITE);
	}
}
