package softeer2nd.chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import softeer2nd.chess.domain.pieces.Piece;

public class Board {

	private static final int BOARD_MAX_INDEX = 8;

	private final List<Rank> boards = new ArrayList<>();
	private final List<Piece> blackPieces = new ArrayList<>();
	private final List<Piece> whitePieces = new ArrayList<>();

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

		initializePiece();
	}

	private void initializePiece() {
		blackPieces.add(Piece.createPiece(Piece.Color.BLACK, Piece.Type.PAWN));
		blackPieces.add(Piece.createPiece(Piece.Color.BLACK, Piece.Type.BISHOP));
		blackPieces.add(Piece.createPiece(Piece.Color.BLACK, Piece.Type.ROOK));
		blackPieces.add(Piece.createPiece(Piece.Color.BLACK, Piece.Type.KING));
		blackPieces.add(Piece.createPiece(Piece.Color.BLACK, Piece.Type.QUEEN));
		blackPieces.add(Piece.createPiece(Piece.Color.BLACK, Piece.Type.KNIGHT));

		whitePieces.add(Piece.createPiece(Piece.Color.WHITE, Piece.Type.PAWN));
		whitePieces.add(Piece.createPiece(Piece.Color.WHITE, Piece.Type.BISHOP));
		whitePieces.add(Piece.createPiece(Piece.Color.WHITE, Piece.Type.ROOK));
		whitePieces.add(Piece.createPiece(Piece.Color.WHITE, Piece.Type.KING));
		whitePieces.add(Piece.createPiece(Piece.Color.WHITE, Piece.Type.QUEEN));
		whitePieces.add(Piece.createPiece(Piece.Color.WHITE, Piece.Type.KNIGHT));
	}

	public List<Piece> getBlackPieces() {
		return Collections.unmodifiableList(blackPieces);
	}

	public List<Piece> getWhitePieces() {
		return Collections.unmodifiableList(whitePieces);
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
		return calculatePawnPoint(color) + calculateExceptPawnPoint(color);
	}

	private double calculatePawnPoint(Piece.Color color) {
		double pawnScore = 0.0d;
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			final long pawnCount = countPawnInColumn(color, i);
			if (pawnCount > 1)
				pawnScore += pawnCount * Piece.Type.PAWN.getDefaultPoint() / 2;
			else
				pawnScore += pawnCount * Piece.Type.PAWN.getDefaultPoint();
		}
		return pawnScore;
	}

	private double calculateExceptPawnPoint(Piece.Color color) {
		return boards.stream()
			.map(rank -> rank.getScoreExceptPawnInRank(color))
			.reduce(0.0d, Double::sum);

	}

	public void sortAscendingAllPieces() {
		blackPieces.sort(Comparator.comparingDouble(a -> a.getType().getDefaultPoint()));
		whitePieces.sort(Comparator.comparingDouble(a -> a.getType().getDefaultPoint()));
	}
}
