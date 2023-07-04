package softeer2nd.chess;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

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
	void initialize() {
		assertThat(board.getPawnsResult(Pawn.WHITE_COLOR)).isEqualTo("pppppppp");
		assertThat(board.getPawnsResult(Pawn.BLACK_COLOR)).isEqualTo("PPPPPPPP");
	}

	@Test
	@DisplayName("Board 초기화 후 테스트시 제대로 출력이 되야한다")
	void print() {
		// given
		OutputStream out = new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));

		// when
		board.print();

		// then
		assertThat(out.toString()).hasToString("........\n"
			+ "PPPPPPPP\n"
			+ "........\n"
			+ "........\n"
			+ "........\n"
			+ "........\n"
			+ "pppppppp\n"
			+ "........\n성");
	}

	private void verifyBoardSize(final int size) {
		assertThat(board.size()).isEqualTo(size);
	}

	private void verifyBoardFindPawn(final String index, final Pawn pawn) {
		assertThat(board.findPawn(index)).isEqualTo(pawn);
	}
}
