package softeer2nd;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
