package softeer2nd.chess.domain.pieces;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.intializer.BoardInitializer;
import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.exception.ExceptionMessage;

class BlankTest {

	@Test
	@DisplayName("기물이 없는곳은 움직일 수 없습니다")
	void blankCanNotMove() {
		// given
		Board board = new Board(new BoardInitializer());
		Chess chess = new Chess(board);
		board.initializeEmpty();

		// when, then
		Assertions.assertThatThrownBy(() -> chess.movePiece("a1", "a2", Color.WHITE))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.BLANK_PIECE_CAN_NOT_MOVE);
	}
}