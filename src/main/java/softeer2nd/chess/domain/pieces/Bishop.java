package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Bishop extends Piece{
	protected Bishop(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
	}

	@Override
	public boolean checkPieceCanGo(Position sourcePosition, Position destinationPosition) {
		return false;
	}
}
