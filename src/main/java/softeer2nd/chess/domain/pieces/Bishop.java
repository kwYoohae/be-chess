package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;

public class Bishop extends Piece {
	protected Bishop(Color color, Type type) {
		super(color, type);
		this.directions = Direction.diagonalDirection();
	}

	@Override
	public Direction getMovableDirection(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = targetPosition.getX() - sourcePosition.getX();
		final int subtractY = targetPosition.getY() - sourcePosition.getY();

		return getDirectionAllPlace(subtractX, subtractY);
	}
}
