package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.exception.ExceptionMessage.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

	@ParameterizedTest
	@DisplayName("퀸은 대각선, 상하좌우 이외의 곳은 움직일 수 없어야한다")
	@ValueSource(strings = {"f3", "b3", "b5", "f5"})
	void canNotGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece queen = Piece.createQueen(Piece.Color.WHITE, new Position(position));
		board.move(position, queen);

		// then
		assertThatThrownBy(() -> board.move(position, destination))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}

	@Test
	@DisplayName("퀸은 같은 편의 기물로는 이동할 수 없다")
	void canNotGoSameColor() {
		// given
		board.initializeEmpty();

		String position = "d4";
		String pawnPosition = "d5";
		final Piece queen = Piece.createQueen(Piece.Color.WHITE, new Position(position));
		final Piece pawn = Piece.createPawn(Piece.Color.WHITE, new Position(pawnPosition));
		board.move(position, queen);
		board.move(pawnPosition, pawn);

		// when, then
		assertThatThrownBy(() -> board.move(position, pawnPosition))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);
	}
}