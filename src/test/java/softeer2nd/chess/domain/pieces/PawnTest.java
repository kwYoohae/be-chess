package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;

class PawnTest {

	private Chess chess;
	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chess = new Chess(board);
	}

	@Test
	@DisplayName("폰은 처음에는 2칸을 갈 수 있다")
	void firstMoveSecondNorthOrSouth() {
		// given
		board.initialize();

		// when
		chess.movePiece("b2", "b4");
		chess.movePiece("b7", "b5");

		//then
		assertThat(board.findPiece("b4")).isEqualTo(Piece.createPiece(Piece.Color.WHITE, new Position("b4"), Piece.Type.PAWN));
		assertThat(board.findPiece("b5")).isEqualTo(Piece.createPiece(Piece.Color.BLACK, new Position("b5"), Piece.Type.PAWN));
	}

	@Test
	@DisplayName("폰은 대각선에 상대편 기물이 있으면 대각선으로 공격할 수 있다")
	void pawnAttackDiagonal() {
		// given
		board.initializeEmpty();

		String sourcePosition = "d2";
		String targetPosition = "c3";
		board.addPiece(sourcePosition, Piece.createPiece(Piece.Color.WHITE, new Position(sourcePosition), Piece.Type.PAWN));
		board.addPiece(targetPosition, Piece.createPiece(Piece.Color.BLACK, new Position(targetPosition), Piece.Type.PAWN));

		// when
		chess.movePiece(sourcePosition, targetPosition);

		// then
		assertThat(board.findPiece(targetPosition)).isEqualTo(Piece.createPiece(Piece.Color.WHITE, new Position(targetPosition), Piece.Type.PAWN));
	}
}