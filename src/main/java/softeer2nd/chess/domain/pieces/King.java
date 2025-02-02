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
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		validatePieceMove(sourcePosition, targetPosition);
	}
}
