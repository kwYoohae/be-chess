package softeer2nd.chess.domain.board;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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
		final Position position = Position.from(input);
		final int x = position.getX();
		final int y = position.getY();

		return boards.get(y).getPiece(x);
	}

	public void initializeEmpty() {
		boards.clear();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			boards.add(Rank.initializeBlank());
		}
	}

	public void move(final String input, final Piece piece) {
		final Position position = Position.from(input);
		final int x = position.getX();
		final int y = position.getY();

		boards.get(y).move(x, piece);
	}

	public boolean checkSamePawnInColum(final Piece.Color color) {
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			if (countPawnInColumn(color, i) > 1)
				return true;
		}
		return false;
	}

	private long countPawnInColumn(final Piece.Color color, int xPos) {
		return IntStream.range(0, BOARD_MAX_INDEX)
			.filter((i) -> {
				final Rank rank = boards.get(i);
				return rank.isPawn(xPos, color);
			})
			.count();
	}

	public double calculatePoint(final Piece.Color color) {
		double pawnScore = 0.0d;
		double rookScore = 0.0d;
		double knightScore = 0.0d;
		double bishopScore = 0.0d;
		double queenScore = 0.0d;

		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			final Rank rank = boards.get(i);
			final long pawnCount = countPawnInColumn(color, i);
			if (pawnCount > 1)
				pawnScore += pawnCount * Piece.Type.PAWN.getDefaultPoint() / 2;
			else
				pawnScore += pawnCount * Piece.Type.PAWN.getDefaultPoint();

			rookScore += rank.pieceCount(Piece.Type.ROOK, color) * Piece.Type.ROOK.getDefaultPoint();
			knightScore += rank.pieceCount(Piece.Type.KNIGHT, color) * Piece.Type.KNIGHT.getDefaultPoint();
			bishopScore += rank.pieceCount(Piece.Type.BISHOP, color) * Piece.Type.BISHOP.getDefaultPoint();
			queenScore += rank.pieceCount(Piece.Type.QUEEN, color) * Piece.Type.QUEEN.getDefaultPoint();
		}

		return pawnScore + rookScore + knightScore + bishopScore + queenScore;
	}
}
