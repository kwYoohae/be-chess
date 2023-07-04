package softeer2nd.chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Pawn;

class BoardTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@Test
	void initialize() {
		Board board = new Board();
		board.initialize();
		assertThat(board.getPawnsResult(Pawn.WHITE_COLOR)).isEqualTo("pppppppp");
		assertThat(board.getPawnsResult(Pawn.BLACK_COLOR)).isEqualTo("PPPPPPPP");
	}

	private void verifyBoardSize(final int size) {
		assertThat(board.size()).isEqualTo(size);
	}

	private void verifyBoardFindPawn(final String index, final Pawn pawn) {
		assertThat(board.findPawn(index)).isEqualTo(pawn);
	}
}
