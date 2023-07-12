package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;
public class King extends Piece {

	protected King(Color color, Type type, Position position) {
		super(color, type, position);
		this.directions = Direction.everyDirection();
	}

	@Override
	public Direction getPieceDirection(final Position sourcePosition, final Position targetPosition) {
		return findDirection(sourcePosition, targetPosition);
	}
}
