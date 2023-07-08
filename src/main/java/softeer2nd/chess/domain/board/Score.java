package softeer2nd.chess.domain.board;

import java.util.List;

import softeer2nd.chess.domain.pieces.Piece;

public class Score {

	private final Board board;

	public Score(final Board board) {
		this.board = board;
	}

	public double calculatePoint(final Piece.Color color) {
		return calculatePawnPoint(color) + calculateExceptPawnPoint(color);
	}

	private double calculatePawnPoint(Piece.Color color) {
		double pawnScore = 0.0d;
		for (int i = 0; i < Board.BOARD_MAX_INDEX; i++) {
			final long pawnCount = board.countPawnInColumn(color, i);
			pawnScore += calculatePawnPointInLine(pawnCount);
		}
		return pawnScore;
	}

	private double calculatePawnPointInLine(long pawnCount) {
		if (pawnCount > 1)
			return pawnCount * Piece.Type.PAWN.getDefaultPoint() / 2;
		return pawnCount * Piece.Type.PAWN.getDefaultPoint();
	}

	private double calculateExceptPawnPoint(Piece.Color color) {
		final List<Rank> boards = board.getBoards();
		return boards.stream()
			.map(rank -> rank.getScoreExceptPawnInRank(color))
			.reduce(0.0d, Double::sum);
	}
}
