package softeer2nd.chess;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.utils.StringUtils.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Piece;

class RankTest {

	@Test
	@DisplayName("초기화를 담당하는 정적 팩터리 메서드는 제대로 동작해야한다")
	void initialize() {
		// given
		final Rank whitePawnsRank = Rank.initializePawn(Piece.Color.WHITE);
		final Rank blackPawnsRank = Rank.initializePawn(Piece.Color.BLACK);
		final Rank blankRank = Rank.initializeBlank();
		final Rank whiteInitialRank = Rank.initializeOtherPieces(Piece.Color.WHITE);
		final Rank blackInitialRank = Rank.initializeOtherPieces(Piece.Color.BLACK);

		// when, then
		assertThat(whitePawnsRank.showPieceLine()).isEqualTo(appendNewLine("pppppppp"));
		assertThat(blackPawnsRank.showPieceLine()).isEqualTo(appendNewLine("PPPPPPPP"));
		assertThat(blankRank.showPieceLine()).isEqualTo(appendNewLine("........"));
		assertThat(blackInitialRank.showPieceLine()).isEqualTo(appendNewLine("RNBQKBNR"));
		assertThat(whiteInitialRank.showPieceLine()).isEqualTo(appendNewLine("rnbqkbnr"));
	}
}