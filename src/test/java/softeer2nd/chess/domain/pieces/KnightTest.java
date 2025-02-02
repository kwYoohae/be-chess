package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

class KnightTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@ParameterizedTest
	@DisplayName("나이트는 한칸뛰고 대각선으로 움직일 수 있다")
	@ValueSource(strings = {"e6", "c6", "f5", "f3", "c2", "e2", "b5", "b3"})
	void canGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece knight = Piece.createKnight(Piece.Color.WHITE, new Position(position));
		board.move(position, knight);

		// when
		board.move(position, destination);

		// then
		assertThat(board.findPiece(destination)).isEqualTo(knight);
	}

	@ParameterizedTest
	@DisplayName("나이트는 한칸뛰고 대각선으로만 갈 수 있다")
	@ValueSource(strings = {"d5", "d3", "c4", "e4"})
	void canNotGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece knight = Piece.createKnight(Piece.Color.WHITE, new Position(position));
		board.move(position, knight);

		// when, then
		assertThatThrownBy(() -> board.move(position, destination))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}
}