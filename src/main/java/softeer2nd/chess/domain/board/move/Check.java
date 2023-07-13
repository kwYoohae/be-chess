package softeer2nd.chess.domain.board.move;

import java.util.List;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;

public class Check {

	private final Board board;
	private final MoveValidator moveValidator;

	public Check(Board board, MoveValidator moveValidator) {
		this.board = board;
		this.moveValidator = moveValidator;
	}

	public boolean checkMate(final Color color) {
		for (int y = 0; y < Board.BOARD_MAX_INDEX; y++) {
			if (checkInLinePiece(color, y))
				return true;
		}
		return false;
	}

	private boolean checkInLinePiece(final Color color, final int y) {
		final Color oppositeColor = Color.getOppositeColor(color);
		for (int x = 0; x < Board.BOARD_MAX_INDEX; x++) {
			final Piece piece = board.findPiece(x, y);
			final Position position = new Position(x, y);
			if (piece.getColor() == oppositeColor && checkInPiece(color, piece, position))
				return true;
		}
		return false;
	}

	private boolean checkInPiece(final Color color, final Piece piece, final Position sourcePosition) {
		if (piece.getType() == Piece.Type.PAWN) {
			return checkPawn(color, piece, sourcePosition);
		}

		if (!piece.isRecursive()) {
			return checkNoRecursive(color, piece, sourcePosition);
		}

		return checkRecursive(color, piece, sourcePosition);
	}

	private boolean checkRecursive(final Color color, final Piece piece, final Position sourcePosition) {
		final List<Piece.Direction> directions = piece.getDirections();
		for (int i = 1; i <= Board.BOARD_MAX_INDEX; i++){
			if (findRecursiveKing(color, sourcePosition, directions, i))
				return true;
		}
		return false;
	}

	private boolean findRecursiveKing(final Color color, final Position sourcePosition, final List<Piece.Direction> directions,
		final int multiplyNumber) {
		return directions.stream()
			.anyMatch(direction -> {
				int x = sourcePosition.getX() + direction.getXDegree() * multiplyNumber;
				int y = sourcePosition.getY() + direction.getYDegree() * multiplyNumber;

				if (!validPosition(x) || !validPosition(y))
					return false;

				final Piece piece = board.findPiece(x, y);
				return piece.getColor() == color && piece.getType() == Piece.Type.KING;
			});
	}

	private boolean checkPawn(final Color color, final Piece piece, final Position sourcePosition) {
		final List<Piece.Direction> directions = Piece.Direction.pawnAttackDirection(piece.getColor());
		return directions.stream().anyMatch(direction -> {
			int x = sourcePosition.getX() + direction.getXDegree();
			int y = sourcePosition.getY() + direction.getYDegree();
			if (!validPosition(x) || !validPosition(y))
				return false;
			final Piece findPiece = board.findPiece(x, y);
			return findPiece.getColor() == color && findPiece.getType() == Piece.Type.KING;
		});
	}

	private boolean checkNoRecursive(final Color turn, final Piece piece, final Position sourcePosition) {
		final List<Piece.Direction> directions = piece.getDirections();
		return directions.stream().anyMatch(direction -> {
			int checkX = sourcePosition.getX() + direction.getXDegree();
			int checkY = sourcePosition.getY() + direction.getYDegree();
			if (!validPosition(checkX) || !validPosition(checkY))
				return false;
			final Position targetPosition = new Position(checkX, checkY);
			return findKing(turn, sourcePosition, targetPosition, direction);
		});
	}

	private boolean findKing(final Color color, final Position sourcePosition, final Position targetPosition,
		final Piece.Direction direction) {
		final Piece piece = board.findPiece(targetPosition.getOrigin());
		if (piece.getColor() == Color.getOppositeColor(color) && piece.getType() == Piece.Type.KING) {
			return moveValidator.validPieceExistsInDirection(sourcePosition, targetPosition, direction);
		}
		return false;
	}

	private boolean validPosition(int position) {
		return position >= 0 && position < Board.BOARD_MAX_INDEX;
	}
}
