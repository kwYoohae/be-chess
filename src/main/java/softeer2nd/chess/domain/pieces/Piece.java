package softeer2nd.chess.domain.pieces;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Objects;

import softeer2nd.chess.domain.board.Position;

public class Piece {

	private final Color color;
	private final Type type;
	private Position position;

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
		return createPiece(Color.NOCOLOR, Type.NO_PIECE);
	}

	private static Piece createBlack(Type type) {
		return createPiece(Color.BLACK, type);
	}

	private static Piece createWhite(Type type) {
		return createPiece(Color.WHITE, type);
	}

	public static Piece createPiece(Color color, Type type) {
		if ((color == Color.NOCOLOR && type != Type.NO_PIECE) || (color != Color.NOCOLOR && type == Type.NO_PIECE)) {
			throw new IllegalArgumentException(NOT_EXIST_PIECE);
		}
		return new Piece(color, type);
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
		PAWN('p', 1.0), ROOK('r', 5.0), KNIGHT('n', 2.5), BISHOP('b',
			3.0), QUEEN('q', 9.0), KING('k', 0.0), NO_PIECE('.', 0.0);

		private final char representation;
		private final double defaultPoint;
		Type(final char representation, final double defaultPoint) {
			this.representation = representation;
			this.defaultPoint = defaultPoint;
		}

		public char getWhiteRepresentation() {
			return representation;
		}

		public char getBlackRepresentation() {
			return Character.toUpperCase(representation);
		}
		public double getDefaultPoint() {
			return defaultPoint;
		}
	}

}
