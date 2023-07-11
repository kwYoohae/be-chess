package softeer2nd.chess.domain;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.Score;
import softeer2nd.chess.domain.pieces.Piece;
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

	public void movePiece(final String sourcePosition, final String targetPosition) {
		validSelfPositionMove(sourcePosition, targetPosition);

		final Piece sourcePiece = board.findPiece(sourcePosition);
		final Piece targetPiece = board.findPiece(targetPosition);

		if (checkOpportunityPieceInDestination(sourcePiece, targetPiece))

		board.move(sourcePosition, targetPosition);
	}

	private void validSelfPositionMove(final String sourcePosition, final String targetPosition) {
		if (sourcePosition.equals(targetPosition)) {
			throw new IllegalArgumentException(PIECE_NOT_MOVE_SELF_POSITION);
		}
	}

	private boolean checkOpportunityPieceInDestination(final Piece sourcePiece, final Piece targetPiece) {
		if (sourcePiece.getColor() == targetPiece.getColor())
			return false;
		return true;
	}
}
