package softeer2nd.chess.domain.pieces;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import softeer2nd.chess.domain.board.position.Position;

public abstract class Piece {

	protected Color color;
	protected Type type;
	protected Position position;
	protected List<Direction> directions = new ArrayList<>();

	public static Piece createPiece(Color color, Position position, Type type) {
		validatePiece(color, type);
		switch (type) {
			case KING:
				return new King(color, type, position);
			case PAWN:
				return new Pawn(color, type, position);
			case ROOK:
				return new Rook(color, type, position);
			case QUEEN:
				return new Queen(color, type, position);
			case BISHOP:
				return new Bishop(color, type, position);
			case KNIGHT:
				return new Knight(color, type, position);
			default:
				return new Blank(Color.NOCOLOR, Type.NO_PIECE, position);
		}
	}

	public static Piece createBlank(Position position) {
		return new Blank(Color.NOCOLOR, Type.NO_PIECE, position);
	}

	public static void validatePiece(Color color, Type type) {
		if ((color == Color.NOCOLOR && type != Type.NO_PIECE) || (color != Color.NOCOLOR && type == Type.NO_PIECE)) {
			throw new IllegalArgumentException(NOT_EXIST_PIECE);
		}
	}

	public Color getColor() {
		return color;
	}

	public Type getType() {
		return type;
	}

	public void setPosition(Position position) {
		this.position = position;
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

	public abstract Direction getPieceDirection(final Position sourcePosition, final Position targetPosition);

	protected Direction getDirectionRecursive(final int subtractX, final int subtractY, final int multiplyNumber) {
		if (multiplyNumber == 8)
			return Direction.EMPTY;

		Direction result = directions.stream()
			.filter(direction -> {
				final int xDegree = direction.getXDegree() * multiplyNumber;
				final int yDegree = direction.getYDegree() * multiplyNumber;
				return xDegree == subtractX && yDegree == subtractY;
			})
			.findAny()
			.orElse(Direction.EMPTY);

		if (result == Direction.EMPTY) {
			result = getDirectionRecursive(subtractX, subtractY, multiplyNumber + 1);
			return result;
		}

		return result;
	}

	protected Direction findDirection(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = targetPosition.getX() - sourcePosition.getX();
		final int subtractY = targetPosition.getY() - sourcePosition.getY();

		return directions.stream()
			.filter(direction -> direction.getXDegree() == subtractX && direction.getYDegree() == subtractY)
			.findAny()
			.orElse(Direction.EMPTY);
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
		PAWN('p', 1.0),
		ROOK('r', 5.0),
		KNIGHT('n', 2.5),
		BISHOP('b', 3.0),
		QUEEN('q', 9.0),
		KING('k', 0.0),
		NO_PIECE('.', 0.0);

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

	public enum Direction {
		NORTH(0, 1),
		DOUBLE_NORTH(0, 2),
		NORTHEAST(1, 1),
		EAST(1, 0),
		SOUTHEAST(1, -1),
		SOUTH(0, -1),
		DOUBLE_SOUTH(0, -2),
		SOUTHWEST(-1, -1),
		WEST(-1, 0),
		NORTHWEST(-1, 1),

		NNE(1, 2),
		NNW(-1, 2),
		SSE(1, -2),
		SSW(-1, -2),
		EEN(2, 1),
		EES(2, -1),
		WWN(-2, 1),
		WWS(-2, -1),

		EMPTY(0, 0);

		private final int xDegree;
		private final int yDegree;

		private Direction(int xDegree, int yDegree) {
			this.xDegree = xDegree;
			this.yDegree = yDegree;
		}

		public int getXDegree() {
			return xDegree;
		}

		public int getYDegree() {
			return yDegree;
		}

		public static List<Direction> everyDirection() {
			return Arrays.asList(NORTH, EAST, SOUTH, WEST, NORTHEAST, SOUTHEAST, SOUTHWEST, NORTHWEST);
		}

		public static List<Direction> linearDirection() {
			return Arrays.asList(NORTH, EAST, SOUTH, WEST);
		}

		public static List<Direction> diagonalDirection() {
			return Arrays.asList(NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST);
		}

		public static List<Direction> whitePawnDirection() {
			return Arrays.asList(NORTH, NORTHEAST, NORTHWEST, DOUBLE_NORTH);
		}

		public static List<Direction> blackPawnDirection() {
			return Arrays.asList(SOUTH, SOUTHWEST, SOUTHEAST, DOUBLE_SOUTH);
		}

		public static List<Direction> knightDirection() {
			return Arrays.asList(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
		}
	}

}
