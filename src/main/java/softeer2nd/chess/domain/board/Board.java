package softeer2nd.chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;

public class Board {

	public static final int BOARD_MAX_INDEX = 8;
	private static final int BLANK_START_INDEX = 3;
	private static final int BLANK_END_INDEX = 6;
	private static final int WHITE_PAWN_START_INDEX = 2;
	private static final int BLACK_PAWN_START_INDEX = 7;

	private final List<Rank> boards = new ArrayList<>();

	public void initialize() {
		boards.clear();
		boards.add(Rank.initializeOtherPieces(Color.WHITE));
		boards.add(Rank.initializePawn(Color.WHITE, WHITE_PAWN_START_INDEX));
		initializeBlanks();
		boards.add(Rank.initializePawn(Color.BLACK, BLACK_PAWN_START_INDEX));
		boards.add(Rank.initializeOtherPieces(Color.BLACK));
	}

	private void initializeBlanks() {
		IntStream.range(BLANK_START_INDEX, BLANK_END_INDEX + 1)
			.forEach((index) -> boards.add(Rank.initializeBlank(index)));
	}

	public String getPawnsResult(Color color) {
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

	public long pieceCount(Piece.Type type, Color color) {
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

	public void addPiece(final String input, final Piece piece) {
		final Position position = new Position(input);
		final int x = position.getX();
		final int y = position.getY();

		final Rank rank = boards.get(y);
		rank.move(x, piece);
	}

	public boolean checkSamePawnInColum(final Color color) {
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			if (countPawnInColumn(color, i) > 1)
				return true;
		}
		return false;
	}

	public long countPawnInColumn(final Color color, int xPos) {
		return IntStream.range(0, BOARD_MAX_INDEX)
			.filter((i) -> {
				final Rank rank = boards.get(i);
				return rank.isPawn(xPos, color);
			})
			.count();
	}

	public void move(final String sourcePosition, final String targetPosition) {
		final Piece sourcePiece = findPiece(sourcePosition);

		addPiece(targetPosition, sourcePiece);
		addPiece(sourcePosition, Piece.createBlank());
	}

	public List<Rank> getBoards() {
		return Collections.unmodifiableList(boards);
	}

	public Rank getRankInIndex(int index) {
		return boards.get(index);
	}
}
