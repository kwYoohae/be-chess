package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Pawn extends Piece {

	private static final int BLACK_PAWN_START_INDEX = 6;
	private static final int WHITE_PAWN_START_INDEX = 1;

	protected Pawn(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		setPawnDirection(color);
	}

	@Override
	public Direction getPieceDirection(final Position sourcePosition, final Position targetPosition) {
		return findDirection(sourcePosition, targetPosition);
	}

	@Override
	protected Direction findDirection(final Position sourcePosition, final Position targetPosition) {
		Direction direction = super.findDirection(sourcePosition, targetPosition);

		if (direction == Direction.DOUBLE_SOUTH || direction == Direction.DOUBLE_NORTH) {
			direction = checkFirstMove(sourcePosition, targetPosition, direction);
		}

		return direction;
	}

	private Direction checkFirstMove(final Position sourcePosition, final Position targetPosition,
		final Direction direction) {
		if (color == Color.WHITE && sourcePosition.getY() != WHITE_PAWN_START_INDEX) {
			return Direction.EMPTY;
		}
		if (color == Color.BLACK && sourcePosition.getY() != BLACK_PAWN_START_INDEX) {
			return Direction.EMPTY;
		}
		return direction;
	}

	private void setPawnDirection(Color color) {
		if (color == Color.BLACK) {
			this.directions = Direction.blackPawnDirection();
			return;
		}
		this.directions = Direction.whitePawnDirection();
	}
}
