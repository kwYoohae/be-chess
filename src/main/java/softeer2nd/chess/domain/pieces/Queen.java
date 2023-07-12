package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Queen extends Piece {

	protected Queen(Color color, Type type, Position position) {
		super(color, type, position);
		directions = Direction.everyDirection();
	}

	@Override
	public Direction getPieceDirection(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = targetPosition.getX() - sourcePosition.getX();
		final int subtractY = targetPosition.getY() - sourcePosition.getY();

		return getDirectionRecursive(subtractX, subtractY, 1);
	}
}
