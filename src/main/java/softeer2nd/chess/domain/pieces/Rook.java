package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;

public class Rook extends Piece {

	protected Rook(Color color, Type type, Position position) {
		super(color, type, position);
		this.directions = Direction.linearDirection();
	}

	@Override
	public Direction getPieceDirection(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = targetPosition.getX() - sourcePosition.getX();
		final int subtractY = targetPosition.getY() - sourcePosition.getY();

		return getDirectionRecursive(subtractX, subtractY, 1);
	}
}
