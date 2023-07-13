package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.intializer.BoardInitializer;
import softeer2nd.chess.domain.pieces.component.Color;

class RankTest {

	@Test
	@DisplayName("piece의 개수를 세는 메서드는 blank 기물을 빼고 계산을 해야한다")
	void pieceCount() {
		// given
		BoardInitializer boardInitializer = new BoardInitializer();
		final Rank blackPawnsRank = boardInitializer.initializePawn(Color.BLACK,1);
		final Rank blankRank = boardInitializer.initializeBlank(2);
		final Rank whitePawnRank = boardInitializer.initializePawn(Color.WHITE, 3);

		// when, then
		assertThat(blackPawnsRank.pieceCount()).isEqualTo(8);
		assertThat(blankRank.pieceCount()).isZero();
		assertThat(whitePawnRank.pieceCount()).isEqualTo(8);
	}
}