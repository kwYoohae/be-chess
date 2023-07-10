package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;

public class Queen extends Piece{

	public Queen(Color color, Type type, Position position) {
		this.color = color;
		this.type = type;
		this.position = position;
	}
}
