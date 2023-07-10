package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

public class Rook extends Piece{

	protected Rook(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
		this.directions = Direction.linearDirection();
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		final int subtractX = targetPosition.getX() - sourcePosition.getX();
		final int subtractY = targetPosition.getY() - sourcePosition.getY();

		if (!checkRecursive(subtractX, subtractY, 1)) {
			throw new IllegalArgumentException(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}
	}
}
