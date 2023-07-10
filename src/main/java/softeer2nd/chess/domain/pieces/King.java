package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

public class King extends Piece{

	protected King(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		this.directions = Direction.everyDirection();
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		int sourceX = sourcePosition.getX();
		int sourceY = sourcePosition.getY();
		int targetX = targetPosition.getX();
		int targetY = targetPosition.getY();

		int subtractX = sourceX - targetX;
		int subtractY = sourceY - targetY;

		final boolean isCanGo = directions.stream()
			.anyMatch(direction -> direction.getXDegree() == subtractX && direction.getYDegree() == subtractY);

		if (!isCanGo) {
			throw new IllegalArgumentException(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}
	}
}
