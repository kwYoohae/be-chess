package softeer2nd.chess.domain.pieces;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;

public class Pawn extends Piece {

	private static final int BLACK_PAWN_START_INDEX = 6;
	private static final int WHITE_PAWN_START_INDEX = 1;

	protected Pawn(Color color, Type type) {
		super(color, type);
		setPawnDirection(color);
	}

	@Override
	public Direction getMovableDirection(final Position sourcePosition, final Position targetPosition) {
		return findDirection(sourcePosition, targetPosition);
	}

	@Override
	public boolean isRecursive() {
		return false;
	}

	@Override
	protected Direction findDirection(final Position sourcePosition, final Position targetPosition) {
		Direction direction = super.findDirection(sourcePosition, targetPosition);

		if (direction == Direction.DOUBLE_SOUTH || direction == Direction.DOUBLE_NORTH) {
			validFirstMove(sourcePosition);
		}

		return direction;
	}

	private void validFirstMove(final Position sourcePosition) {
		if (color == Color.WHITE && sourcePosition.getY() != WHITE_PAWN_START_INDEX) {
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}
		if (color == Color.BLACK && sourcePosition.getY() != BLACK_PAWN_START_INDEX) {
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}
	}

	private void setPawnDirection(Color color) {
		if (color == Color.BLACK) {
			this.directions = Direction.blackPawnDirection();
			return;
		}
		this.directions = Direction.whitePawnDirection();
	}
}
