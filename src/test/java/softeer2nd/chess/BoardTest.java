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
	void create() throws Exception {
		final Pawn white = new Pawn(Pawn.WHITE_COLOR);
		board.add(white);
		verifyBoardSize(1);
		verifyBoardFindPawn(0, white);

		final Pawn black = new Pawn(Pawn.BLACK_COLOR);
		board.add(black);
		verifyBoardSize(2);
		verifyBoardFindPawn(1, black);
	}

	@Test
	@DisplayName("board에서 저장된 것보다 많은 Pawn은 불러올 수 없어야 한다")
	void getSizeOverPawn() {
		// given
		final Pawn pawn = new Pawn(Pawn.WHITE_COLOR);
		board.add(pawn);

		// when, then
		assertThatThrownBy(() -> board.findPawn(2))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(BOARD_HAS_NOT_OVER_THE_SAVE_PAWN);
	}

	private void verifyBoardSize(final int size) {
		assertThat(board.size()).isEqualTo(size);
	}

	private void verifyBoardFindPawn(final int index, final Pawn pawn) {
		assertThat(board.findPawn(index)).isEqualTo(pawn);
	}
}
