package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;

class QueenTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}


	@ParameterizedTest
	@DisplayName("퀸은 상, 하, 좌, 우, 대각선으로 어디든 움직일 수 있다")
	@ValueSource(strings = {"c4", "d3", "e4", "d5", "c3", "e3", "e5", "c5", "d8", "d1",
	"a4", "h4", "g1", "a1", "a7", "h8"})
	void canGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece queen = Piece.createQueen(Piece.Color.WHITE, new Position(position));
		board.move(position, queen);

		// when
		board.move(position, destination);

		// then
		assertThat(board.findPiece(destination)).isEqualTo(queen);
	}
}