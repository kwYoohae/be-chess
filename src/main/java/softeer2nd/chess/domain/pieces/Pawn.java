package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Pawn extends Piece{

	private static final int BLACK_PAWN_START_INDEX = 6;
	private static final int WHITE_PAWN_START_INDEX = 1;

	protected Pawn(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		setPawnDirection(color);
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		if (checkFirstMove(sourcePosition, targetPosition))
			return;

		validatePieceMove(sourcePosition, targetPosition);
	}

	private boolean checkFirstMove(final Position sourcePosition, final Position targetPosition) {
		final int y = sourcePosition.getY();

		if (y != BLACK_PAWN_START_INDEX && y != WHITE_PAWN_START_INDEX)
			return false;

		Direction direction = directions.get(0);
		final int subtractY = targetPosition.getY() - sourcePosition.getY();
		final int subtractX = targetPosition.getX() - sourcePosition.getX();

		if (subtractX == direction.getXDegree() * 2 && subtractY == direction.getYDegree() * 2) {
			return true;
		}
		return false;
	}

	private void setPawnDirection(Color color) {
		if (color == Color.BLACK) {
			this.directions = Direction.blackPawnDirection();
			return;
		}
		this.directions = Direction.whitePawnDirection();
	}
}
