package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;

public class Queen extends Piece {

	protected Queen(Color color, Type type) {
		super(color, type);
		directions = Direction.everyDirection();
	}

	@Override
	public Direction getMovableDirection(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = targetPosition.getX() - sourcePosition.getX();
		final int subtractY = targetPosition.getY() - sourcePosition.getY();

		return getDirectionAllPlace(subtractX, subtractY);
	}

	@Override
	public boolean isRecursive() {
		return true;
	}
}
