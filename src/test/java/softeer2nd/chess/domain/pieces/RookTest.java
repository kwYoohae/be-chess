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

class RookTest {

	private Chess chess;
	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chess = new Chess(board);
	}

	@ParameterizedTest
	@DisplayName("룩은 상, 하, 좌, 우 어디든 움직일 수 있다")
	@ValueSource(strings = {"e4", "d3", "c4", "d5", "d8", "d1", "a4", "h4"})
	void canGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece rook = Piece.createPiece(Piece.Color.WHITE, new Position(position), Piece.Type.ROOK);
		board.addPiece(position, rook);

		// when
		chess.movePiece(position, destination);

		// then
		assertThat(board.findPiece(destination)).isEqualTo(rook);
	}

	@ParameterizedTest
	@DisplayName("룩은 대각선으로는 움직일 수 없다")
	@ValueSource(strings = {"e5", "e3", "c3", "c5"})
	void canNotGo(String destination) {
		// given
		board.initializeEmpty();

		String position = "d4";
		final Piece rook = Piece.createPiece(Piece.Color.WHITE, new Position(position), Piece.Type.ROOK);
		board.addPiece(position, rook);

		// when
		assertThatThrownBy(() -> chess.movePiece(position, destination))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}
}