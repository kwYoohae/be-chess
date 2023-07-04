package softeer2nd.chess;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.Board;
import softeer2nd.chess.pieces.Pawn;

public class BoardTest {

	@Test
	@DisplayName("board에 Pawn이 제대로 추가 되어야 한다")
	public void create() throws Exception {
		Board board = new Board();

		final Pawn white = new Pawn(Pawn.WHITE_COLOR);
		board.add(white);
		assertThat(board.size()).isEqualTo(1);
		assertThat(board.findPawn(0)).isEqualTo(white);

		final Pawn black = new Pawn(Pawn.BLACK_COLOR);
		board.add(black);
		assertThat(board.size()).isEqualTo(2);
		assertThat(board.findPawn(1)).isEqualTo(black);
	}

	@Test
	@DisplayName("board에서 저장된 것보다 많은 Pawn은 불러올 수 없어야 한다")
	public void getSizeOverPawn() {
		// given
		final Board board = new Board();

		final Pawn pawn = new Pawn(Pawn.WHITE_COLOR);
		board.add(pawn);

		// when, then
		assertThatThrownBy(() -> board.findPawn(2))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("저장된 갯수보다 많은 Pawn은 불러올 수 없습니다");
	}
}
