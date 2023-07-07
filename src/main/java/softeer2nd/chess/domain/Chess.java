package softeer2nd.chess.domain;

import softeer2nd.chess.domain.board.Board;

public class Chess {

	private final Board board;

	public Chess() {
		board = new Board();
	}

	public Chess(Board board) {
		this.board = board;
	}

	public void initializeBoard() {
		board.initialize();
	}

	public String showBoard() {
		return board.showBoard();
	}

	public void movePiece(String sourcePosition, String targetPosition) {
		board.move(sourcePosition, targetPosition);
	}
}
