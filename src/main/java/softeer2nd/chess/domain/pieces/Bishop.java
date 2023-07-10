package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

public class Bishop extends Piece{
	protected Bishop(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		this.directions = Direction.diagonalDirection();
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = sourcePosition.getX() - targetPosition.getX();
		final int subtractY = sourcePosition.getY() - targetPosition.getY();

		if (!checkRecursive(subtractX, subtractY, 1)) {
			throw new IllegalArgumentException(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}
	}

	private boolean checkRecursive(final int subtractX, final int subtractY, final int multiplyNumber) {
		if (multiplyNumber == 8)
			return false;

		final boolean isCanGo = directions.stream()
			.anyMatch(direction -> {
				final int xDegree = direction.getXDegree() * multiplyNumber;
				final int yDegree = direction.getYDegree() * multiplyNumber;

				return xDegree == subtractX && subtractY == yDegree;
			});

		if (!isCanGo) {
			return checkRecursive(subtractX, subtractY, multiplyNumber + 1);
		}

		return true;
	}
}
