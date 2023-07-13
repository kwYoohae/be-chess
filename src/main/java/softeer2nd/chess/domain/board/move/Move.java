package softeer2nd.chess.domain.board.move;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.Piece.Direction;
import softeer2nd.chess.domain.pieces.component.Color;

public class Move {

	private final Board board;

	public Move(Board board) {
		this.board = board;
	}

	public void movePiece(final String sourceInput, final String targetInput, final Color turn) {
		validMovableTurn(sourceInput, turn);
		validSelfPosition(sourceInput, targetInput);

		final Position sourcePosition = new Position(sourceInput);
		final Position targetPosition = new Position(targetInput);
		validMovable(sourcePosition, targetPosition);
		validOpportunityPieceInDestination(sourcePosition, targetPosition);

		board.move(sourceInput, targetInput);
	}

	private void validMovableTurn(final String sourceInput, final Color turn) {
		final Piece piece = board.findPiece(sourceInput);

		if (piece.getColor() != turn) {
			throw new IllegalArgumentException(USER_MOVABLE_OWN_TURUN);
		}
	}

	private void validSelfPosition(final String sourceInput, final String targetInput) {
		if (sourceInput.equals(targetInput))
			throw new IllegalArgumentException(PIECE_NOT_MOVE_SELF_POSITION);
	}

	private void validMovable(final Position sourcePosition, final Position targetPosition) {
		final Piece movePiece = board.findPiece(sourcePosition.getOrigin());
		final Direction movableDirection = movePiece.getMovableDirection(sourcePosition, targetPosition);

		if (movePiece.getType() == Piece.Type.KNIGHT)
			return;

		if (movePiece.getType() == Piece.Type.PAWN) {
			validPawnMovable(sourcePosition, targetPosition, movableDirection);
			return;
		}

		validPieceExistsInDirection(sourcePosition, targetPosition, movableDirection);
	}

	private void validPieceExistsInDirection(Position sourcePosition, Position targtPosition, Direction direction) {
		int x = sourcePosition.getX() + direction.getXDegree();
		int y = sourcePosition.getY() + direction.getYDegree();

		while (targtPosition.getX() != x || targtPosition.getY() != y) {
			final Piece piece = board.findPiece(x, y);
			if (piece.getType() != Piece.Type.NO_PIECE)
				throw new IllegalArgumentException(PIECE_JUMP_TO_PIECE);
			x += direction.getXDegree();
			y += direction.getYDegree();
		}
	}

	private void validPawnMovable(final Position sourcePosition, final Position targetPosition, final Direction direction) {
		if (direction == Direction.NORTH || direction == Direction.SOUTH)
			return;

		if (direction == Direction.DOUBLE_NORTH) {
			validPieceExistsInDirection(sourcePosition, targetPosition, Direction.NORTH);
			return;
		}
		if (direction == Direction.DOUBLE_SOUTH) {
			validPieceExistsInDirection(sourcePosition, targetPosition, Direction.SOUTH);
			return;
		}
		validPawnDiagonalMovable(targetPosition);
	}

	private void validPawnDiagonalMovable(final Position targetPosition) {
		final Piece piece = board.findPiece(targetPosition.getOrigin());
		if (piece.getType() == Piece.Type.NO_PIECE)
			throw new IllegalArgumentException(PAWN_CAN_MOVE_DIAGONAL_ONLY_TARGET_POSITION_HAVE_ENEMY);
	}

	private void validOpportunityPieceInDestination(final Position sourcePosition, final Position targetPosition) {
		final Piece sourcePiece = board.findPiece(sourcePosition.getOrigin());
		final Piece targetPiece = board.findPiece(targetPosition.getOrigin());
		if (sourcePiece.getColor() == targetPiece.getColor())
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);
	}
}
