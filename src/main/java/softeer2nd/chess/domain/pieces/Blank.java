package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

public class Blank extends Piece{

	protected Blank(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		throw new IllegalArgumentException(ExceptionMessage.BLANK_PIECE_CAN_NOT_MOVE);
	}
}
