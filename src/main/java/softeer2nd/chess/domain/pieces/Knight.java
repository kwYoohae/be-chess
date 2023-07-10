package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Knight extends Piece{

	protected Knight(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		directions = Direction.knightDirection();
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		validatePieceMove(sourcePosition, targetPosition);
	}
}
