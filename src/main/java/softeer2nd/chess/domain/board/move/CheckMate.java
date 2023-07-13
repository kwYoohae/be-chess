package softeer2nd.chess.domain.board.move;

import softeer2nd.chess.domain.board.Board;

public class CheckMate {

	private final Board board;

	public CheckMate(Board board) {
		this.board = board;
	}

	// public boolean isCheckMate(Color turn) {
	// 	for (int y = 0; y < Board.BOARD_MAX_INDEX; y++) {
	// 		if (checkMateInLine(turn, y)) {
	// 			return true;
	// 		}
	// 	}
	// 	return false;
	// }
	//
	// private boolean checkMateInLine(final Color turn, final int y) {
	// 	for (int x = 0; x < Board.BOARD_MAX_INDEX; x++) {
	// 		final Piece piece = board.findPiece(x, y);
	//
	// 	}
	// }
	//
	// private boolean findKing(final Color turn, final Piece piece, final int x, final int y) {
	// 	final List<Piece.Direction> directions = piece.getDirections();
	// 	directions.stream()
	// 		.filter(direction -> {
	// 			int checkPositionX = direction.getXDegree() + x;
	// 			int checkPositionY = direction.getYDegree() + y;
	// 		})
	// }
	//
	// private boolean findKingRecursive(final Color turn, final Piece piece, final int x, final int y) {
	// 	final List<Piece.Direction> directions = piece.getDirections();
	//
	// 	for (int i = 1; i <= Board.BOARD_MAX_INDEX; i++) {
	// 		final Piece.Direction direction = directions.get(i);
	// 		int checkPositionX = (direction.getXDegree() + x) * i;
	// 		int checkPositionY = (direction.getYDegree() + y) * i;
	//
	// 		if (!validPosition(checkPositionX) || !validPosition(checkPositionY)) {
	// 			continue;
	// 		}
	//
	// 		final Piece findPiece = board.findPiece(checkPositionX, checkPositionY);
	// 		if (findPiece.getType() == Piece.Type.KING && findPiece.getColor() == Color.getOppositeColor(turn)) {
	//
	// 			return true;
	// 		}
	// 	}
	// }
	//
	// private boolean validPosition(int position) {
	// 	return position >= 0 && position < Board.BOARD_MAX_INDEX;
	// }

}
