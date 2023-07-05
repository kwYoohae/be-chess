package softeer2nd.chess.pieces;

import java.util.Objects;

public class Piece {

	public static final String WHITE_COLOR = "white";
	public static final String BLACK_COLOR = "black";
	public static final String WHITE_PAWN_REPRESENTATION = "p";
	public static final String WHITE_KNIGHT_REPRESENTATION = "n";
	public static final String WHITE_ROOK_REPRESENTATION = "r";
	public static final String WHITE_BISHOP_REPRESENTATION = "b";
	public static final String WHITE_QUEEN_REPRESENTATION = "q";
	public static final String WHITE_KING_REPRESENTATION = "k";
	public static final String BLACK_PAWN_REPRESENTATION = "P";
	public static final String BLACK_KNIGHT_REPRESENTATION = "N";
	public static final String BLACK_ROOK_REPRESENTATION = "R";
	public static final String BLACK_BISHOP_REPRESENTATION = "B";
	public static final String BLACK_QUEEN_REPRESENTATION = "Q";
	public static final String BLACK_KING_REPRESENTATION = "K";
	public static final String WHITE_START_LOCATION = "2";
	public static final String BLACK_START_LOCATION = "7";

	private final String color;
	private final String representation;

	private Piece(String color, String representation) {
		this.color = color;
		this.representation = representation;
	}

	public static Piece createWhitePawn() {
		return new Piece(WHITE_COLOR, WHITE_PAWN_REPRESENTATION);
	}

	public static Piece createWhiteKnight() {
		return new Piece(WHITE_COLOR, WHITE_KNIGHT_REPRESENTATION);
	}

	public static Piece createWhiteRook() {
		return new Piece(WHITE_COLOR, WHITE_ROOK_REPRESENTATION);
	}

	public static Piece createWhiteBishop() {
		return new Piece(WHITE_COLOR, WHITE_BISHOP_REPRESENTATION);
	}

	public static Piece createWhiteQueen() {
		return new Piece(WHITE_COLOR, WHITE_QUEEN_REPRESENTATION);
	}

	public static Piece createWhiteKing() {
		return new Piece(WHITE_COLOR, WHITE_KING_REPRESENTATION);
	}

	public static Piece createBlackPawn() {
		return new Piece(BLACK_COLOR, BLACK_PAWN_REPRESENTATION);
	}

	public static Piece createBlackKnight() {
		return new Piece(BLACK_COLOR, BLACK_KNIGHT_REPRESENTATION);
	}

	public static Piece createBlackRook() {
		return new Piece(BLACK_COLOR, BLACK_ROOK_REPRESENTATION);
	}

	public static Piece createBlackBishop() {
		return new Piece(BLACK_COLOR, BLACK_BISHOP_REPRESENTATION);
	}

	public static Piece createBlackQueen() {
		return new Piece(BLACK_COLOR, BLACK_QUEEN_REPRESENTATION);
	}

	public static Piece createBlackKing() {
		return new Piece(BLACK_COLOR, BLACK_KING_REPRESENTATION);
	}

	public String getColor() {
		return color;
	}

	public String getRepresentation() {
		return representation;
	}

	public boolean isBlack() {
		return color.equals(BLACK_COLOR);
	}

	public boolean isWhite() {
		return color.equals(WHITE_COLOR);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Piece piece = (Piece)o;
		return Objects.equals(color, piece.color) && Objects.equals(representation,
			piece.representation);
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, representation);
	}
}
