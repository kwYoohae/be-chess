package softeer2nd.chess.domain;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.Score;
import softeer2nd.chess.domain.board.move.Check;
import softeer2nd.chess.domain.board.move.MoveValidator;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.view.ChessView;

public class Chess {

	private final Board board;
	private final Score score;
	private final ChessView chessView;
	private final MoveValidator moveValidator;
	private final Check check;

	public Chess(Board board) {
		this.board = board;
		score = new Score(board);
		chessView = new ChessView(board);
		moveValidator = new MoveValidator(board);
		check = new Check(board, moveValidator);
	}

	public void initializeBoard() {
		board.initialize();
	}

	public String showBoard() {
		return chessView.showBoard();
	}

	public void movePiece(final String sourceInput, final String targetInput, final Color turn) {
		moveValidator.validMovableTurn(sourceInput, turn);
		moveValidator.validSelfPosition(sourceInput, targetInput);

		final Position sourcePosition = new Position(sourceInput);
		final Position targetPosition = new Position(targetInput);
		moveValidator.validMovable(sourcePosition, targetPosition);
		moveValidator.validOpportunityPieceInDestination(sourcePosition, targetPosition);

		board.move(sourceInput, targetInput);
	}

	public boolean isChecked(Color color) {
		return check.checkMate(color);
	}

	public boolean isKingAlive(Color color) {
		return board.isKingAlive(color);
	}
}
