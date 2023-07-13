package softeer2nd.chess.domain;

import org.junit.jupiter.api.BeforeEach;

import softeer2nd.chess.domain.board.Board;

class ChessTest {

	private Board board;
	private Chess chess;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chess = new Chess(board);
	}

}