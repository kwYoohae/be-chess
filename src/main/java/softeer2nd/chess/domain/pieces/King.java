package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class King extends Piece{

	protected King(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		this.direction = Direction.everyDirection();
	}

	@Override
	public boolean checkPieceCanGo(Position sourcePosition, Position destinationPosition) {
		int sourceX = sourcePosition.getX();
		int sourceY = sourcePosition.getY();
		int destinationX = destinationPosition.getX();
		int destinationY = destinationPosition.getY();

		int subtractX = sourceX - destinationX;
		int subtractY = sourceY - destinationY;

		return direction.stream()
			.anyMatch(direction -> direction.getXDegree() == subtractX && direction.getYDegree() == subtractY);
	}
}
