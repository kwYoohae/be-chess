package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.pieces.Piece;

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
	void initialize() {
		board.initialize();

		assertThat(board.getPawnsResult(Piece.Color.WHITE)).isEqualTo("pppppppp");
		assertThat(board.getPawnsResult(Piece.Color.BLACK)).isEqualTo("PPPPPPPP");
	}
}
