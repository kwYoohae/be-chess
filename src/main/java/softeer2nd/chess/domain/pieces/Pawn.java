package softeer2nd.chess.domain.pieces;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

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
		final int subtractX = sourcePosition.getX() - targetPosition.getX();
		final int subtractY = sourcePosition.getY() - targetPosition.getY();

		final boolean isCanGo = directions.stream()
			.anyMatch(direction -> direction.getXDegree() == subtractX && direction.getYDegree() == subtractY);

		if (!isCanGo) {
			throw new IllegalArgumentException(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}
	}

	private void setPawnDirection(Color color) {
		if (color == Color.BLACK) {
			this.directions = Direction.blackPawnDirection();
			return;
		}
		this.directions = Direction.whitePawnDirection();
	}
}
