package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.domain.pieces.Piece.Type.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.exception.ExceptionMessage;

class PieceTest {

	private Chess chess;
	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chess = new Chess(board);
	}

	@Test
	@DisplayName("흰색과 검은색 기물들이 모두 생성 되어야 한다")
	void create_piece() {
		verifyPawn(Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.BLACK,
				PAWN), PAWN);
		verifyPawn(Piece.createPiece(Color.WHITE, KNIGHT),
			Piece.createPiece(Color.BLACK, KNIGHT), KNIGHT);
		verifyPawn(Piece.createPiece(Color.WHITE, ROOK),
			Piece.createPiece(Color.BLACK, ROOK), ROOK);
		verifyPawn(Piece.createPiece(Color.WHITE, BISHOP),
			Piece.createPiece(Color.BLACK, BISHOP), BISHOP);
		verifyPawn(Piece.createPiece(Color.WHITE, QUEEN),
			Piece.createPiece(Color.BLACK, QUEEN), QUEEN);
		verifyPawn(Piece.createPiece(Color.WHITE, KING),
			Piece.createPiece(Color.BLACK, KING), KING);

		Piece blank = Piece.createBlank();
		assertThat(blank.isBlack()).isFalse();
		assertThat(blank.isWhite()).isFalse();
		assertThat(blank.getType()).isEqualTo(NO_PIECE);
	}

	@Test
	@DisplayName("기물들의 색은 생성한 그대로여야 한다")
	void checkColor() {
		verifyColor(Piece.createPiece(Color.BLACK, KING), Color.BLACK);
		verifyColor(Piece.createPiece(Color.BLACK, PAWN), Color.BLACK);
		verifyColor(Piece.createPiece(Color.BLACK, KNIGHT),Color.BLACK);
		verifyColor(Piece.createPiece(Color.BLACK, QUEEN), Color.BLACK);
		verifyColor(Piece.createPiece(Color.BLACK, BISHOP), Color.BLACK);
		verifyColor(Piece.createPiece(Color.BLACK, ROOK), Color.BLACK);

		verifyColor(Piece.createPiece(Color.WHITE, KING), Color.WHITE);
		verifyColor(Piece.createPiece(Color.WHITE, PAWN), Color.WHITE);
		verifyColor(Piece.createPiece(Color.WHITE, KNIGHT), Color.WHITE);
		verifyColor(Piece.createPiece(Color.WHITE, QUEEN), Color.WHITE);
		verifyColor(Piece.createPiece(Color.WHITE, BISHOP), Color.WHITE);
		verifyColor(Piece.createPiece(Color.WHITE, ROOK), Color.WHITE);
	}

	@Test
	@DisplayName("존재하지 않는 기물을 생성할 경우 에러가 발생한다")
	void notExistsPiece() {
		assertThatThrownBy(() -> Piece.createPiece(Color.NOCOLOR, PAWN))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.NOT_EXIST_PIECE);
	}

	@Test
	@DisplayName("자기팀의 기물로는 이동할 수 없습니다")
	void canNotGoSameTeam() {
		// given
		board.initializeEmpty();

		String queenPosition = "d4";
		String pawnPosition = "d5";
		String kingPosition = "d6";
		String rookPosition = "c5";
		String bishopPosition = "e6";
		String knightPosition = "f4";
		final Piece queen = Piece.createPiece(Color.WHITE, QUEEN);
		final Piece king = Piece.createPiece(Color.WHITE, KING);
		final Piece pawn = Piece.createPiece(Color.WHITE, PAWN);
		final Piece rook = Piece.createPiece(Color.WHITE, ROOK);
		final Piece bishop = Piece.createPiece(Color.WHITE, BISHOP);
		final Piece knight = Piece.createPiece(Color.WHITE, KNIGHT);

		board.addPiece(pawnPosition, pawn);

		// when, then
		verifyMoveSameColor(queenPosition, pawnPosition, queen, Color.WHITE);
		verifyMoveSameColor(kingPosition, pawnPosition, king, Color.WHITE);
		verifyMoveSameColor(rookPosition, pawnPosition, rook, Color.WHITE);
		verifyMoveSameColor(bishopPosition, pawnPosition, bishop, Color.WHITE);
		verifyMoveSameColor(knightPosition, pawnPosition, knight, Color.WHITE);

	}

	@Test
	@DisplayName("나이트를 제외한 기물은 넘어다닐 수 없습니다")
	void canNotGoJumpOtherPiece() {
		// given
		board.initialize();

		// when, then
		assertThatThrownBy(() -> chess.movePiece("h1", "h3", Color.WHITE))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_JUMP_TO_PIECE);
	}


	void verifyMoveSameColor(String sourcePosition, String targetPosition, Piece piece, Color color) {
		board.addPiece(sourcePosition, piece);

		assertThatThrownBy(() -> chess.movePiece(sourcePosition, targetPosition, color))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);
	}

	void verifyColor(Piece piece, final Color color) {
		if (color == Color.WHITE) {
			assertThat(piece.isWhite()).isTrue();
			assertThat(piece.isBlack()).isFalse();
		}

		if (color == Color.BLACK) {
			assertThat(piece.isBlack()).isTrue();
			assertThat(piece.isWhite()).isFalse();
		}
	}

	void verifyPawn(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
		assertThat(whitePiece.isWhite()).isTrue();
		assertThat(whitePiece.getType()).isEqualTo(type);

		assertThat(blackPiece.isBlack()).isTrue();
		assertThat(blackPiece.getType()).isEqualTo(type);
	}
}
