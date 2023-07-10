package softeer2nd.chess.domain;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.Score;
import softeer2nd.chess.view.ChessView;

public class Chess {

	private final Board board;
	private final Score score;
	private final ChessView chessView;

	public Chess(Board board) {
		this.board = board;
		score = new Score(board);
		chessView = new ChessView(board);
	}

	public void initializeBoard() {
		board.initialize();
	}

	public String showBoard() {
		return chessView.showBoard();
	}

	public void movePiece(String sourcePosition, String targetPosition) {
		board.move(sourcePosition, targetPosition);
	}
}
