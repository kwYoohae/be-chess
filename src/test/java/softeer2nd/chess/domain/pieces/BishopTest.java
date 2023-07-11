package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

class BishopTest {

	private Chess chess;
	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chess = new Chess(board);
	}

	@ParameterizedTest
	@DisplayName("비숍은 대각선 어디든 움직일 수 있다")
	@ValueSource(strings = {"c5", "c3", "e3", "e5", "a7", "a1", "g1", "h8"})
	void canGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece bishop = Piece.createPiece(Piece.Color.WHITE, new Position(position), Piece.Type.BISHOP);
		board.addPiece(position, bishop);

		// when
		chess.movePiece(position, destination);

		// then
		assertThat(board.findPiece(destination)).isEqualTo(bishop);
	}

	@ParameterizedTest
	@DisplayName("비숍은 대각선 이외에 다른곳으로 움직일 수 없다")
	@ValueSource(strings = {"e4", "c4", "d5", "d3"})
	void canNotGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece bishop = Piece.createPiece(Piece.Color.WHITE, new Position(position), Piece.Type.BISHOP);
		board.addPiece(position, bishop);

		// when
		assertThatThrownBy(() -> chess.movePiece(position, destination))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}
}