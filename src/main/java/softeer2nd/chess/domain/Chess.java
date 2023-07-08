package softeer2nd.chess.domain;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.Score;

public class Chess {

	private final Board board;
	private final Score score;

	public Chess(Board board) {
		this.board = board;
		score = new Score(board);
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
