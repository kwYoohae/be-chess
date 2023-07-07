package softeer2nd.chess.domain.pieces;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Objects;

import softeer2nd.chess.domain.board.position.Position;

public class Piece {

	private final Color color;
	private final Type type;
	private Position position;

	private Piece(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
	}

	public static Piece createWhitePawn(Position position) {
		return createWhite(Type.PAWN, position);
	}

	public static Piece createWhiteKnight(Position position) {
		return createWhite(Type.KNIGHT, position);
	}

	public static Piece createWhiteRook(Position position) {
		return createWhite(Type.ROOK, position);
	}

	public static Piece createWhiteBishop(Position position) {
		return createWhite(Type.BISHOP, position);
	}

	public static Piece createWhiteQueen(Position position) {
		return createWhite(Type.QUEEN, position);
	}

	public static Piece createWhiteKing(Position position) {
		return createWhite(Type.KING, position);
	}

	public static Piece createBlackPawn(Position position) {
		return createBlack(Type.PAWN, position);
	}

	public static Piece createBlackKnight(Position position) {
		return createBlack(Type.KNIGHT, position);
	}

	public static Piece createBlackRook(Position position) {
		return createBlack(Type.ROOK, position);
	}

	public static Piece createBlackBishop(Position position) {
		return createBlack(Type.BISHOP, position);
	}

	public static Piece createBlackQueen(Position position) {
		return createBlack(Type.QUEEN, position);
	}

	public static Piece createBlackKing(Position position) {
		return createBlack(Type.KING, position);
	}

	public static Piece createBlank(Position position) {
		return createPiece(Color.NOCOLOR, Type.NO_PIECE, position);
	}

	private static Piece createBlack(Type type, Position position) {
		return createPiece(Color.BLACK, type, position);
	}

	private static Piece createWhite(Type type, Position position) {
		return createPiece(Color.WHITE, type, position);
	}

	public static Piece createPiece(Color color, Type type, Position position) {
		if ((color == Color.NOCOLOR && type != Type.NO_PIECE) || (color != Color.NOCOLOR && type == Type.NO_PIECE)) {
			throw new IllegalArgumentException(NOT_EXIST_PIECE);
		}
		return new Piece(color, type, position);
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

	public Position getPosition() {
		return position;
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
