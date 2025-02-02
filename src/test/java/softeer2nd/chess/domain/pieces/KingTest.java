package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
	void canNotGo() {
		// given
		board.initializeEmpty();

		String position = "b4";
		final Piece king = Piece.createKing(Piece.Color.WHITE, new Position(position));
		board.move(position, king);

		// when, then
		assertThatThrownBy(() -> {
				board.move("b4", "b7");
			}).isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}

	@ParameterizedTest
	@DisplayName("킹은 상, 하, 좌, 우, 대각선으로 1칸을 움직일 수 있다")
	@ValueSource(strings = {"b5", "b3", "c4", "a4", "a5", "c5", "c3", "a3"})
	void canGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "b4";
		final Piece king = Piece.createKing(Piece.Color.WHITE, new Position(position));
		board.move(position, king);

		// when
		board.move(position, destination);

		// then
		assertThat(board.findPiece(destination)).isEqualTo(king);
	}
}