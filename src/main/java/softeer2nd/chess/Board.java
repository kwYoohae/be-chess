package softeer2nd.chess;

import static softeer2nd.chess.pieces.Piece.*;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private static final int BOARD_MAX_INDEX = 8;

	private final List<Rank> boards = new ArrayList<>();

	public void initialize() {
		boards.clear();
		boards.add(Rank.initializeOtherPieces(Color.WHITE));
		boards.add(Rank.initializePawn(Color.WHITE));
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializeBlank());
		boards.add(Rank.initializePawn(Color.BLACK));
		boards.add(Rank.initializeOtherPieces(Color.BLACK));
	}

	public String getPawnsResult(Color color) {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX - 1; i >= 0; i--) {
			final Rank row = boards.get(i);
			sb.append(row.showLineWantPiece(Type.PAWN, color));
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
}
