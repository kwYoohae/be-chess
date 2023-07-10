package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Blank extends Piece{

	protected Blank(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
	}

	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {

	}
}
