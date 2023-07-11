package softeer2nd.chess.domain.board;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;

public class Board {

	public static final int BOARD_MAX_INDEX = 8;
	private static final int BLANK_START_INDEX = 3;
	private static final int BLANK_END_INDEX = 6;
	private static final int WHITE_PAWN_START_INDEX = 2;
	private static final int BLACK_PAWN_START_INDEX = 7;

	private final List<Rank> boards = new ArrayList<>();

	public void initialize() {
		boards.clear();
		boards.add(Rank.initializeOtherPieces(Piece.Color.WHITE));
		boards.add(Rank.initializePawn(Piece.Color.WHITE, WHITE_PAWN_START_INDEX));
		initializeBlanks();
		boards.add(Rank.initializePawn(Piece.Color.BLACK, BLACK_PAWN_START_INDEX));
		boards.add(Rank.initializeOtherPieces(Piece.Color.BLACK));
	}

	private void initializeBlanks() {
		IntStream.range(BLANK_START_INDEX, BLANK_END_INDEX + 1)
			.forEach((index) -> boards.add(Rank.initializeBlank(index)));
	}

	public String getPawnsResult(Piece.Color color) {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX - 1; i >= 0; i--) {
			final Rank row = boards.get(i);
			sb.append(row.showLineWantPiece(Piece.Type.PAWN, color));
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	public long pieceCount() {
		return boards.stream()
			.map(Rank::pieceCount)
			.reduce(0L, Long::sum);
	}

	public long pieceCount(Piece.Type type, Piece.Color color) {
		return boards.stream()
			.map(row -> row.pieceCount(type, color))
			.reduce(0L, Long::sum);
	}

	public Piece findPiece(final String input) {
		final Position position = new Position(input);
		final int x = position.getX();
		final int y = position.getY();

		final Rank rank = boards.get(y);
		return rank.getPiece(x);
	}

	public Piece findPiece(final int x, final int y) {
		final Rank rank = boards.get(y);
		return rank.getPiece(x);
	}

	public void initializeEmpty() {
		boards.clear();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			boards.add(Rank.initializeBlank(i + 1));
		}
	}

	public void move(final String input, final Piece piece) {
		final Position position = new Position(input);
		final int x = position.getX();
		final int y = position.getY();

		final Rank rank = boards.get(y);
		piece.setPosition(position);
		rank.move(x, piece);
	}

	public boolean checkSamePawnInColum(final Piece.Color color) {
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			if (countPawnInColumn(color, i) > 1)
				return true;
		}
		return false;
	}

	public long countPawnInColumn(final Piece.Color color, int xPos) {
		return IntStream.range(0, BOARD_MAX_INDEX)
			.filter((i) -> {
				final Rank rank = boards.get(i);
				return rank.isPawn(xPos, color);
			})
			.count();
	}

	public boolean checkAllPieceInDirection(Position source, Position target, Piece.Direction direction) {
		int x = source.getX() + direction.getXDegree();
		int y = source.getX() + direction.getYDegree();

		while (target.getX() != x && target.getY() != y) {
			final Piece piece = findPiece(x, y);
			if (piece.getType() != Piece.Type.NO_PIECE)
				return false;
			x += direction.getXDegree();
			y += direction.getYDegree();
		}
		return true;
	}

	public void move(final String sourcePosition, final String targetPosition) {
		final Piece sourcePiece = findPiece(sourcePosition);

		sourcePiece.getPieceDirection(new Position(sourcePosition), new Position(targetPosition));
		validateSamePieceMovePosition(sourcePosition, targetPosition);

		move(targetPosition, sourcePiece);
		move(sourcePosition, Piece.createBlank(new Position(sourcePosition)));
	}

	private void validateSamePieceMovePosition(final String sourcePosition, final String targetPosition) {
		final Piece sourcePiece = findPiece(sourcePosition);
		final Piece targetPiece = findPiece(targetPosition);

		if (sourcePiece.getColor() == targetPiece.getColor()) {
			throw new IllegalArgumentException(PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);
		}
	}

	public List<Rank> getBoards() {
		return Collections.unmodifiableList(boards);
	}

	public Rank getRankInIndex(int index) {
		return boards.get(index);
	}
}
