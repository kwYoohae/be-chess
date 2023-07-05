package softeer2nd.chess.pieces;

import java.util.Objects;

public class Piece {
	public static final String WHITE_START_LOCATION = "2";
	public static final String BLACK_START_LOCATION = "7";

	private final Color color;
	private final Type type;

	private Piece(Color color, Type type) {
		this.color = color;
		this.type = type;
	}

	public static Piece createWhitePawn() {
		return createWhite(Type.PAWN);
	}

	public static Piece createWhiteKnight() {
		return createWhite(Type.KNIGHT);
	}

	public static Piece createWhiteRook() {
		return createWhite(Type.ROOK);
	}

	public static Piece createWhiteBishop() {
		return createWhite(Type.BISHOP);
	}

	public static Piece createWhiteQueen() {
		return createWhite(Type.QUEEN);
	}

	public static Piece createWhiteKing() {
		return createWhite(Type.KING);
	}

	public static Piece createBlackPawn() {
		return createBlack(Type.PAWN);
	}

	public static Piece createBlackKnight() {
		return createBlack(Type.KNIGHT);
	}

	public static Piece createBlackRook() {
		return createBlack(Type.ROOK);
	}

	public static Piece createBlackBishop() {
		return createBlack(Type.BISHOP);
	}

	public static Piece createBlackQueen() {
		return createBlack(Type.QUEEN);
	}

	public static Piece createBlackKing() {
		return createBlack(Type.KING);
	}

	public static Piece createBlank() {
		return new Piece(Color.NOCOLOR, Type.NO_PIECE);
	}

	private static Piece createBlack(Type type) {
		return new Piece(Color.BLACK, type);
	}

	private static Piece createWhite(Type type) {
		return new Piece(Color.WHITE, type);
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}

	public boolean isBlack() {
		return color == Color.BLACK;
	}

	public boolean isWhite() {
		return color == Color.WHITE;
	}

	public char getRepresentation() {
		if (color == Color.BLACK)
			return type.getBlackRepresentation();
		return type.getWhiteRepresentation();
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		final Piece piece = (Piece)o;
		return color == piece.color && type == piece.type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(color, type);
	}

	public enum Color {
		WHITE, BLACK, NOCOLOR;
	}

	public enum Type {
		PAWN('p'), ROOK('r'), KNIGHT('n'), BISHOP('b'), QUEEN('q'), KING('k'), NO_PIECE('.');

		private final char representation;
		Type(final char representation) {
			this.representation = representation;
		}

		public char getWhiteRepresentation() {
			return representation;
		}

		public char getBlackRepresentation() {
			return Character.toUpperCase(representation);
		}
	}

}
