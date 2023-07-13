package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.exception.ExceptionMessage;

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
		chess.movePiece("b2", "b4", Color.WHITE);
		chess.movePiece("b7", "b5", Color.BLACK);

		//then
		assertThat(board.findPiece("b4")).isEqualTo(
			Piece.createPiece(Color.WHITE, Piece.Type.PAWN));
		assertThat(board.findPiece("b5")).isEqualTo(
			Piece.createPiece(Color.BLACK, Piece.Type.PAWN));
	}

	@Test
	@DisplayName("폰은 대각선에 상대편 기물이 있으면 대각선으로 공격할 수 있다")
	void pawnAttackDiagonal() {
		// given
		board.initializeEmpty();

		String sourcePosition = "d2";
		String targetPosition = "c3";
		addPiece(sourcePosition, Color.WHITE, Piece.Type.PAWN);
		addPiece(targetPosition, Color.BLACK, Piece.Type.PAWN);

		// when
		chess.movePiece(sourcePosition, targetPosition, Color.WHITE);

		// then
		final Piece piece = Piece.createPiece(Color.WHITE, Piece.Type.PAWN);
		assertThat(board.findPiece(targetPosition)).isEqualTo(piece);
	}

	@Test
	@DisplayName("폰이 대각선으로 움직일때 상대 기물이 없으면 오류가 발생한다")
	void pawnAttackDiagonalError() {
		// given
		board.initializeEmpty();

		String sourcePosition = "d2";
		addPiece(sourcePosition, Color.WHITE, Piece.Type.PAWN);

		// when, then
		assertThatThrownBy(() -> chess.movePiece(sourcePosition, "c3", Color.WHITE))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PAWN_CAN_MOVE_DIAGONAL_ONLY_TARGET_POSITION_HAVE_ENEMY);

		assertThatThrownBy(() -> chess.movePiece(sourcePosition, "e3", Color.WHITE))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PAWN_CAN_MOVE_DIAGONAL_ONLY_TARGET_POSITION_HAVE_ENEMY);
	}

	@Test
	@DisplayName("폰은 처음 이동하는것이 아니면 2칸을 이동하지 못한다")
	void pawnSecondMoveCanNotDoubleMove() {
		// given
		board.initialize();

		chess.movePiece("b2", "b3", Color.WHITE);

		// when, then
		assertThatThrownBy(() -> chess.movePiece("b3", "b5", Color.WHITE))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_DESTINATION_POSITION);
	}

	@Test
	@DisplayName("폰은 처음에 2칸을 움직일때, 사이에 다른 기물이 존재하면 가지 못한다")
	void pawnBetweenHavePieceCanNotMoveDouble() {
		// given
		board.initialize();

		addPiece("c3", Color.WHITE, Piece.Type.PAWN);

		// when, then
		assertThatThrownBy(() -> chess.movePiece("c2", "c4", Color.WHITE))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_JUMP_TO_PIECE);
	}

	private void addPiece(String position, Color color, Piece.Type type) {
		final Piece piece = Piece.createPiece(color, type);
		board.addPiece(position, piece);
	}
}