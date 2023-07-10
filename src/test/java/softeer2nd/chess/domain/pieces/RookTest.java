package softeer2nd.chess.domain.pieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;

class RookTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@ParameterizedTest
	@DisplayName("룩은 상, 하, 좌, 우 어디든 움직일 수 있다")
	@ValueSource(strings = {"e4", "d3", "c4", "d5", "d8", "d1", "a4", "h4"})
	void canGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece rook = Piece.createRook(Piece.Color.WHITE, new Position(position));
		board.move(position, rook);

		// when
		board.move(position, destination);

		// then
		assertEquals(board.findPiece(destination), rook);
	}
}