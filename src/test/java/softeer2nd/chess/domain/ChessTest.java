package softeer2nd.chess.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.intializer.BoardInitializer;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;

class ChessTest {

	private Board board;
	private Chess chess;

	@BeforeEach
	void beforeEach() {
		board = new Board(new BoardInitializer());
		chess = new Chess(board);
	}

	@Test
	@DisplayName("블랙 킹이 잡힐 위기면 check라고 값을 반환한다")
	void blackCheck() {
		// given
		board.initializeEmpty();
		board.addPiece("d4", Piece.createPiece(Color.WHITE, Piece.Type.QUEEN));
		board.addPiece("f3", Piece.createPiece(Color.WHITE, Piece.Type.KING));
		board.addPiece("g6", Piece.createPiece(Color.BLACK, Piece.Type.QUEEN));
		board.addPiece("e8", Piece.createPiece(Color.BLACK, Piece.Type.KING));

		// chess.movePiece("d4", "d8", Color.WHITE);
		chess.movePiece("g6", "f6", Color.BLACK);

		// when then
		Assertions.assertThat(chess.isChecked(Color.WHITE)).isTrue();
		Assertions.assertThat(chess.isChecked(Color.BLACK)).isFalse();
	}

	@Test
	@DisplayName("화이트 킹이 잡힐 위기면 check라고 값을 반환한다")
	void whhiteCheck() {
		// given
		board.initializeEmpty();
		board.addPiece("d4", Piece.createPiece(Color.WHITE, Piece.Type.QUEEN));
		board.addPiece("f3", Piece.createPiece(Color.WHITE, Piece.Type.KING));
		board.addPiece("g6", Piece.createPiece(Color.BLACK, Piece.Type.QUEEN));
		board.addPiece("e8", Piece.createPiece(Color.BLACK, Piece.Type.KING));

		// chess.movePiece("d4", "d8", Color.WHITE);
		chess.movePiece("d4", "e4", Color.WHITE);

		// when then
		Assertions.assertThat(chess.isChecked(Color.WHITE)).isFalse();
		Assertions.assertThat(chess.isChecked(Color.BLACK)).isTrue();
	}
}