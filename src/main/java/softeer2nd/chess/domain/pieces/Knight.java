package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

public class Knight extends Piece{

	protected Knight(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		directions = Direction.knightDirection();
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = sourcePosition.getX() - targetPosition.getX();
		final int subtractY = sourcePosition.getY() - targetPosition.getY();

		final boolean isCanGo = directions.stream()
			.anyMatch(direction -> direction.getXDegree() == subtractX && direction.getYDegree() == subtractY);

		if (!isCanGo)
			throw new IllegalArgumentException(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}
}
