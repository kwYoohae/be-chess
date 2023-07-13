package softeer2nd.chess.domain;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.move.Move;
import softeer2nd.chess.domain.board.Score;
import softeer2nd.chess.view.ChessView;

public class Chess {

	private final Board board;
	private final Score score;
	private final ChessView chessView;
	private final Move move;

	public Chess(Board board) {
		this.board = board;
		score = new Score(board);
		chessView = new ChessView(board);
		move = new Move(board);
	}

	public void initializeBoard() {
		board.initialize();
	}

	public String showBoard() {
		return chessView.showBoard();
	}

	public void movePiece(final String sourcePosition, final String targetPosition) {
		move.movePiece(sourcePosition, targetPosition);
	}
}
