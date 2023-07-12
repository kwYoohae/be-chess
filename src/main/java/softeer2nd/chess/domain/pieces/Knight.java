package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Knight extends Piece {

	protected Knight(Color color, Type type, Position position) {
		super(color, type, position);
		directions = Direction.knightDirection();
	}

	@Override
	public Direction getPieceDirection(final Position sourcePosition, final Position targetPosition) {
		return findDirection(sourcePosition, targetPosition);
	}
}
