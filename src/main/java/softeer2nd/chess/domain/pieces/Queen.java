package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

public class Queen extends Piece{

	protected Queen(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		directions = Direction.everyDirection();
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = sourcePosition.getX() - targetPosition.getX();
		final int subtractY = sourcePosition.getY() - targetPosition.getY();

		final boolean isCanGo = checkRecursive(subtractX, subtractY, 1);
		if (!isCanGo) {
			throw new IllegalArgumentException(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}
	}

	private boolean checkRecursive(final int subtractX, final int subtractY, final int mulNumber) {
		if (mulNumber == 8)
			return false;

		final boolean isCanGo = directions.stream()
			.anyMatch(direction -> {
				final int xDegree = direction.getXDegree() * mulNumber;
				final int yDegree = direction.getYDegree() * mulNumber;

				return xDegree == subtractX && yDegree == subtractY;
			});

		if (!isCanGo) {
			return checkRecursive(subtractX, subtractY, mulNumber + 1);
		}

		return true;
	}
}
