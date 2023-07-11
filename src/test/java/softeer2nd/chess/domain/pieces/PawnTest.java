package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;

class PawnTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@Test
	@DisplayName("폰은 처음에는 2칸을 갈 수 있다")
	void firstMoveSecondNorthOrSouth() {
		// given
		board.initialize();

		// when
		board.move("b2", "b4");
		board.move("b7", "b5");

		//then
		assertThat(board.findPiece("b4")).isEqualTo(Piece.createPiece(Piece.Color.WHITE, new Position("b4"), Piece.Type.PAWN));
		assertThat(board.findPiece("b5")).isEqualTo(Piece.createPiece(Piece.Color.BLACK, new Position("b5"), Piece.Type.PAWN));
	}
}