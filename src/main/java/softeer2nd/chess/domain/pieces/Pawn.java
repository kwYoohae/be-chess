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

	//TODO: Step-7에서 Pawn이 초기에는 직진만 가능한것 등등 예외처리 하기
	@Override
	public void checkPieceCanGo(final Position sourcePosition, final Position targetPosition) {
		validatePieceMove(sourcePosition, targetPosition);
	}

	private void setPawnDirection(Color color) {
		if (color == Color.BLACK) {
			this.directions = Direction.blackPawnDirection();
			return;
		}
		this.directions = Direction.whitePawnDirection();
	}
}
