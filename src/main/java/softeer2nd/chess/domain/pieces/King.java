package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class King extends Piece{

	protected King(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		this.directions = Direction.everyDirection();
	}

	@Override
	public boolean checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		int sourceX = sourcePosition.getX();
		int sourceY = sourcePosition.getY();
		int targetX = targetPosition.getX();
		int targetY = targetPosition.getY();

		int subtractX = sourceX - targetX;
		int subtractY = sourceY - targetY;

		return directions.stream()
			.anyMatch(direction -> direction.getXDegree() == subtractX && direction.getYDegree() == subtractY);
	}
}
