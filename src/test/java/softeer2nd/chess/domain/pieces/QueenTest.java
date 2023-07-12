package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.exception.ExceptionMessage.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.component.Color;

class QueenTest {

	private Chess chess;
	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chess = new Chess(board);
	}

	@ParameterizedTest
	@DisplayName("퀸은 상, 하, 좌, 우, 대각선으로 어디든 움직일 수 있다")
	@ValueSource(strings = {"c4", "d3", "e4", "d5", "c3", "e3", "e5", "c5", "d8", "d1",
		"a4", "h4", "g1", "a1", "a7", "h8"})
	void canGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece queen = Piece.createPiece(Color.WHITE, new Position(position), Piece.Type.QUEEN);
		board.addPiece(position, queen);

		// when
		chess.movePiece(position, destination);

		// then
		assertThat(board.findPiece(destination)).isEqualTo(queen);
	}

	@ParameterizedTest
	@DisplayName("퀸은 대각선, 상하좌우 이외의 곳은 움직일 수 없어야한다")
	@ValueSource(strings = {"f3", "b3", "b5", "f5"})
	void canNotGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece queen = Piece.createPiece(Color.WHITE, new Position(position), Piece.Type.QUEEN);
		board.addPiece(position, queen);

		// then
		assertThatThrownBy(() -> chess.movePiece(position, destination))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}
}