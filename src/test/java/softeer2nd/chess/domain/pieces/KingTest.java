package softeer2nd.chess.domain.pieces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

class KingTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@Test
	@DisplayName("킹은 상, 하, 좌, 우, 대각선으로 1칸만 갈 수 있다")
	void canGo() {
		// given
		board.initializeEmpty();

		String position = "b4";
		final Piece king = Piece.createKing(Piece.Color.WHITE, new Position(position));
		board.move(position, king);

		// when, then
		Assertions.assertThatThrownBy(() -> {
				board.move("b4", "b7");
			}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}

}