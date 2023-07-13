package softeer2nd.chess.domain.board.intializer;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.Rank;
import softeer2nd.chess.domain.pieces.component.Color;

class BoardInitializerTest {
	@Test
	@DisplayName("초기화를 담당하는 정적 팩터리 메서드는 제대로 동작해야한다")
	void initialize() {
		// given
		BoardInitializer boardInitialize = new BoardInitializer();

		// when
		final Rank whitePawnsRank = boardInitialize.initializePawn(Color.WHITE, 1);
		final Rank blackPawnsRank = boardInitialize.initializePawn(Color.BLACK, 21);
		final Rank blankRank = boardInitialize.initializeBlank(3);
		final Rank whiteInitialRank = boardInitialize.initializeOtherPieces(Color.WHITE);
		final Rank blackInitialRank = boardInitialize.initializeOtherPieces(Color.BLACK);

		// then
		assertThat(whitePawnsRank.showPieceLine()).isEqualTo(appendNewLine("pppppppp"));
		assertThat(blackPawnsRank.showPieceLine()).isEqualTo(appendNewLine("PPPPPPPP"));
		assertThat(blankRank.showPieceLine()).isEqualTo(appendNewLine("........"));
		assertThat(blackInitialRank.showPieceLine()).isEqualTo(appendNewLine("RNBQKBNR"));
		assertThat(whiteInitialRank.showPieceLine()).isEqualTo(appendNewLine("rnbqkbnr"));
	}
}