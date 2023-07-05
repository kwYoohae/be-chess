package softeer2nd.chess;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.exception.ExceptionMessage.*;
import static softeer2nd.utils.StringUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.pieces.Piece;

class BoardTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@Test
	@DisplayName("board에 기물이 제대로 추가 되어야 한다")
	void create() {
		board.initialize();
		assertThat(board.pieceCount()).isEqualTo(32);
		String blankRank = appendNewLine("........");
		assertThat(board.showBoard()).isEqualTo(
			appendNewLine("RNBQKBNR") +
				appendNewLine("PPPPPPPP") +
				blankRank + blankRank + blankRank + blankRank +
				appendNewLine("pppppppp") +
				appendNewLine("rnbqkbnr")
		);
	}

	@Test
	@DisplayName("board에서 저장된 것보다 많은 Pawn은 불러올 수 없어야 한다")
	void getSizeOverPawn() {
		// given
		board.add(Piece.createWhitePawn());

		// when, then
		assertThatThrownBy(() -> board.findPawn("B2"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(DO_NOT_FIND_PAWN_IN_BOARD);
	}

	@Test
	void initialize() {
		board.initialize();

		assertThat(board.getPawnsResult(Piece.WHITE_COLOR)).isEqualTo("pppppppp");
		assertThat(board.getPawnsResult(Piece.BLACK_COLOR)).isEqualTo("PPPPPPPP");
	}

	private void verifyBoardSize(final int size) {
		assertThat(board.size()).isEqualTo(size);
	}

	private void verifyBoardFindPawn(final String index, final Piece piece) {
		assertThat(board.findPawn(index)).isEqualTo(piece);
	}
}
