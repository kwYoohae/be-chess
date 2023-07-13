package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;

class RankTest {

	@Test
	@DisplayName("piece의 개수를 세는 메서드는 blank 기물을 빼고 계산을 해야한다")
	void pieceCount() {
		// given
		final Piece whitePawnPiece = Piece.createPiece(Color.WHITE, Piece.Type.PAWN);
		final Piece blackPawnPiece = Piece.createPiece(Color.BLACK, Piece.Type.PAWN);
		final Piece blankPiece = Piece.createBlank();
		final Rank blackPawnsRank = new Rank(
			List.of(blackPawnPiece, blackPawnPiece, blackPawnPiece, blackPawnPiece, blackPawnPiece, blackPawnPiece,
				blackPawnPiece, blackPawnPiece));
		final Rank blankRank = new Rank(
			List.of(blankPiece, blankPiece, blankPiece, blankPiece, blankPiece, blankPiece, blankPiece, blankPiece));
		final Rank whiteInitialRank = new Rank(
			List.of(whitePawnPiece, whitePawnPiece, whitePawnPiece, whitePawnPiece, whitePawnPiece, whitePawnPiece,
				whitePawnPiece, whitePawnPiece));

		// when, then
		assertThat(blackPawnsRank.pieceCount()).isEqualTo(8);
		assertThat(blankRank.pieceCount()).isZero();
		assertThat(whiteInitialRank.pieceCount()).isEqualTo(8);
	}
}