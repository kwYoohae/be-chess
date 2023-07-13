package softeer2nd.chess.domain.board.move;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.Piece.Direction;
import softeer2nd.chess.domain.pieces.component.Color;

public class MoveValidator {

	private final Board board;

	public MoveValidator(Board board) {
		this.board = board;
	}

	public void validMovableTurn(final String sourceInput, final Color turn) {
		final Piece piece = board.findPiece(sourceInput);

		if (piece.getColor() == Color.NOCOLOR) {
			throw new IllegalArgumentException(BLANK_PIECE_CAN_NOT_MOVE);
		}
		if (piece.getColor() != turn) {
			throw new IllegalArgumentException(USER_MOVABLE_OWN_TURUN);
		}
	}

	public void validSelfPosition(final String sourceInput, final String targetInput) {
		if (sourceInput.equals(targetInput))
			throw new IllegalArgumentException(PIECE_NOT_MOVE_SELF_POSITION);
	}

	public void validMovable(final Position sourcePosition, final Position targetPosition) {
		final Piece movePiece = board.findPiece(sourcePosition.getOrigin());
		final Direction movableDirection = movePiece.getMovableDirection(sourcePosition, targetPosition);

		if (movePiece.getType() == Piece.Type.KNIGHT)
			return;

		if (movePiece.getType() == Piece.Type.PAWN) {
			validPawnMovable(sourcePosition, targetPosition, movableDirection);
			return;
		}

		if (!validPieceExistsInDirection(sourcePosition, targetPosition, movableDirection)) {
			throw new IllegalArgumentException(PIECE_JUMP_TO_PIECE);
		}
	}

	public boolean validPieceExistsInDirection(Position sourcePosition, Position targtPosition, Direction direction) {
		int x = sourcePosition.getX() + direction.getXDegree();
		int y = sourcePosition.getY() + direction.getYDegree();

		while (targtPosition.getX() != x || targtPosition.getY() != y) {
			final Piece piece = board.findPiece(x, y);
			if (piece.getType() != Piece.Type.NO_PIECE)
				return false;
			x += direction.getXDegree();
			y += direction.getYDegree();
		}
		return true;
	}

	private void validPawnMovable(final Position sourcePosition, final Position targetPosition, final Direction direction) {
		int xDegree = direction.getXDegree();
		int yDegree = direction.getYDegree();

		if (Math.abs(xDegree) == Math.abs(yDegree)) {
			validPawnDiagonalMovable(targetPosition);
			return;
		}

		final Piece piece = board.findPiece(targetPosition.getOrigin());
		if (piece.getType() != Piece.Type.NO_PIECE)
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_DESTINATION_POSITION);

		if (direction == Direction.DOUBLE_SOUTH || direction == Direction.DOUBLE_NORTH)
			validPawnDoubleMovable(sourcePosition, targetPosition, direction);
	}

	private void validPawnDoubleMovable(final Position sourcePosition, final Position targetPosition, Direction direction) {
		if (direction == Direction.DOUBLE_NORTH) {
			direction = Direction.NORTH;
		} else if (direction == Direction.DOUBLE_SOUTH) {
			direction = Direction.SOUTH;
		}

		if (!validPieceExistsInDirection(sourcePosition, targetPosition, direction)) {
			throw new IllegalArgumentException(PIECE_JUMP_TO_PIECE);
		}
	}

	private void validPawnDiagonalMovable(final Position targetPosition) {
		final Piece piece = board.findPiece(targetPosition.getOrigin());
		if (piece.getType() == Piece.Type.NO_PIECE)
			throw new IllegalArgumentException(PAWN_CAN_MOVE_DIAGONAL_ONLY_TARGET_POSITION_HAVE_ENEMY);
	}

	public void validOpportunityPieceInDestination(final Position sourcePosition, final Position targetPosition) {
		final Piece sourcePiece = board.findPiece(sourcePosition.getOrigin());
		final Piece targetPiece = board.findPiece(targetPosition.getOrigin());
		if (sourcePiece.getColor() == targetPiece.getColor())
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);
	}
}
