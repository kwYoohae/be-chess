package softeer2nd.chess.view;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.Rank;

public class ChessView {

	private final Board board;

	public ChessView(Board board) {
		this.board = board;
	}

	public String showBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = Board.BOARD_MAX_INDEX - 1; i >= 0; i--) {
			final Rank rank = board.getRankInIndex(i);
			sb.append(rank.showPieceLine());
		}
		return sb.toString();
	}
}
