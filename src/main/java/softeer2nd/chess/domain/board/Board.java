package softeer2nd.chess.domain.board;

import java.util.ArrayList;
import java.util.List;

import softeer2nd.chess.domain.pieces.Piece;

public class Board {

	private static final int BOARD_MAX_INDEX = 8;

	private final List<Rank> boards = new ArrayList<>();

	public void initialize() {
		boards.clear();
		boards.add(Rank.initializeOtherPieces(Piece.Color.WHITE));
		boards.add(Rank.initializePawn(Piece.Color.WHITE));
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializePawn(Piece.Color.BLACK));
		boards.add(Rank.initializeOtherPieces(Piece.Color.BLACK));
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

	public String showBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX - 1; i >= 0; i--) {
			final Rank row = boards.get(i);
			sb.append(row.showPieceLine());
		}
		return sb.toString();
	}

	public int pieceCount() {
		int sum = 0;
		for (Rank row : boards) {
			sum += row.pieceCount();
		}
		return sum;
	}

	public int pieceCount(Piece.Type type, Piece.Color color) {
		int sum = 0;
		for (Rank row : boards) {
			sum += row.pieceCount(type, color);
		}
		return sum;
	}

	public Piece findPiece(final String input) {
		final Position position = Position.from(input);
		final int x = position.getX();
		final int y = position.getY();

		return null;
	}
}
