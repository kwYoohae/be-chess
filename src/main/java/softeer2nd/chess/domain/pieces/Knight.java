package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;

public class Knight extends Piece {

	protected Knight(Color color, Type type) {
		super(color, type);
		directions = Direction.knightDirection();
	}

	@Override
	public Direction getPieceDirection(final Position sourcePosition, final Position targetPosition) {
		return findDirection(sourcePosition, targetPosition);
	}
}
