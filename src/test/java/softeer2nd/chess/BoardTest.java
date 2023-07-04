package softeer2nd.chess;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.exception.ExceptionMessage.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Pawn;

class BoardTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@Test
	@DisplayName("board에 Pawn이 제대로 추가 되어야 한다")
	void create() {
		final Pawn white = new Pawn(Pawn.WHITE_COLOR);
		board.add(white);
		verifyBoardSize(1);
		verifyBoardFindPawn("A2", white);

		final Pawn black = new Pawn(Pawn.BLACK_COLOR);
		board.add(black);
		verifyBoardSize(2);
		verifyBoardFindPawn("A7", black);
	}

	@Test
	@DisplayName("board에서 저장된 것보다 많은 Pawn은 불러올 수 없어야 한다")
	void getSizeOverPawn() {
		// given
		final Pawn pawn = new Pawn(Pawn.WHITE_COLOR);
		board.add(pawn);

		// when, then
		assertThatThrownBy(() -> board.findPawn("B2"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(DO_NOT_FIND_PAWN_IN_BOARD);
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
