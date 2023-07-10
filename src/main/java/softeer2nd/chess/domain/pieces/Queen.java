package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Queen extends Piece{

	protected Queen(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		directions = Direction.everyDirection();
	}

	@Override
	public boolean checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		final int sourceX = sourcePosition.getX();
		final int sourceY = sourcePosition.getY();
		final int destinationX = targetPosition.getX();
		final int destinationY = targetPosition.getY();

		final int subtractX = sourceX - destinationX;
		final int subtractY = sourceY - destinationY;

		return checkRecursive(subtractX, subtractY, 1);
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
			checkRecursive(subtractX, subtractY, mulNumber + 1);
		}
		return true;
	}
}
