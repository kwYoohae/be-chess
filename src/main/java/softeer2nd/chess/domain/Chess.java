package softeer2nd.chess.domain;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.Score;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.Piece.Direction;
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
		checkMovable(new Position(sourcePosition), new Position(targetPosition));
		validOpportunityPieceInDestination(sourcePosition, targetPosition);

		board.move(sourcePosition, targetPosition);
	}

	private void validSelfPositionMove(final String sourcePosition, final String targetPosition) {
		if (sourcePosition.equals(targetPosition)) {
			throw new IllegalArgumentException(PIECE_NOT_MOVE_SELF_POSITION);
		}
	}

	private void validOpportunityPieceInDestination(final String sourcePosition, final String targetPosition) {
		final Piece sourcePiece = board.findPiece(sourcePosition);
		final Piece targetPiece = board.findPiece(targetPosition);
		if (sourcePiece.getColor() == targetPiece.getColor())
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);
	}

	private void checkMovable(final Position sourcePosition, final Position targetPosition) {
		final Piece piece = board.findPiece(sourcePosition.getX(), sourcePosition.getY());
		Direction direction = piece.getPieceDirection(sourcePosition, targetPosition);
		if (direction == Direction.EMPTY) {
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_DESTINATION_POSITION);
		}

		if (piece.getType() == Piece.Type.KNIGHT)
			return;

		if (piece.getType() == Piece.Type.PAWN) {
			checkPawnMovable(sourcePosition, targetPosition, direction);
			return;
		}

		if (!board.checkAllPieceInDirection(sourcePosition, targetPosition, direction)) {
			throw new IllegalArgumentException(PIECE_JUMP_TO_PIECE);
		}
	}

	private void checkPawnMovable(final Position sourcePosition, final Position targetPosition,
		final Direction direction) {
		if (direction == Direction.NORTH || direction == Direction.SOUTH)
			return;

		if (direction == Direction.DOUBLE_NORTH || direction == Direction.DOUBLE_SOUTH) {
			checkDoubleMovable(sourcePosition, direction);
			return;
		}

		final Piece targetPiece = board.findPiece(targetPosition.getX(), targetPosition.getY());
		if (targetPiece.getType() == Piece.Type.NO_PIECE)
			throw new IllegalArgumentException(PAWN_CAN_MOVE_DIAGONAL_ONLY_TARGET_POSITION_HAVE_ENEMY);
	}

	private void checkDoubleMovable(Position sourcePosition, Direction direction) {
		Direction addDirection = Direction.NORTH;
		if (direction == Direction.DOUBLE_SOUTH)
			addDirection = Direction.SOUTH;

		int x = sourcePosition.getX() + addDirection.getXDegree();
		int y = sourcePosition.getY() + addDirection.getYDegree();
		final Piece piece = board.findPiece(x, y);
		if (piece.getType() != Piece.Type.NO_PIECE)
			throw new IllegalArgumentException(PIECE_JUMP_TO_PIECE);
	}
}
