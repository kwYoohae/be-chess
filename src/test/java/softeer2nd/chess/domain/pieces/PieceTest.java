package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.domain.pieces.Piece.Type.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
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
		verifyPawn(Piece.createPiece(Piece.Color.WHITE, new Position("a1"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("a2"),
				PAWN), PAWN);
		verifyPawn(Piece.createPiece(Piece.Color.WHITE, new Position("b1"), KNIGHT),
			Piece.createPiece(Piece.Color.BLACK, new Position("b2"), KNIGHT), KNIGHT);
		verifyPawn(Piece.createPiece(Piece.Color.WHITE, new Position("c1"), ROOK),
			Piece.createPiece(Piece.Color.BLACK, new Position("c2"), ROOK), ROOK);
		verifyPawn(Piece.createPiece(Piece.Color.WHITE, new Position("d1"), BISHOP),
			Piece.createPiece(Piece.Color.BLACK, new Position("d2"), BISHOP), BISHOP);
		verifyPawn(Piece.createPiece(Piece.Color.WHITE, new Position("e1"), QUEEN),
			Piece.createPiece(Piece.Color.BLACK, new Position("e2"), QUEEN), QUEEN);
		verifyPawn(Piece.createPiece(Piece.Color.WHITE, new Position("f1"), KING),
			Piece.createPiece(Piece.Color.BLACK, new Position("f2"), KING), KING);

		Piece blank = Piece.createBlank(new Position("c3"));
		assertThat(blank.isBlack()).isFalse();
		assertThat(blank.isWhite()).isFalse();
		assertThat(blank.getType()).isEqualTo(NO_PIECE);
	}

	@Test
	@DisplayName("기물들의 색은 생성한 그대로여야 한다")
	void checkColor() {
		verifyColor(Piece.createPiece(Piece.Color.BLACK, new Position("a1"), KING), Piece.Color.BLACK);
		verifyColor(Piece.createPiece(Piece.Color.BLACK, new Position("b1"), PAWN), Piece.Color.BLACK);
		verifyColor(Piece.createPiece(Piece.Color.BLACK, new Position("c1"), KNIGHT), Piece.Color.BLACK);
		verifyColor(Piece.createPiece(Piece.Color.BLACK, new Position("d1"), QUEEN), Piece.Color.BLACK);
		verifyColor(Piece.createPiece(Piece.Color.BLACK, new Position("e1"), BISHOP), Piece.Color.BLACK);
		verifyColor(Piece.createPiece(Piece.Color.BLACK, new Position("f1"), ROOK), Piece.Color.BLACK);

		verifyColor(Piece.createPiece(Piece.Color.WHITE, new Position("a2"), KING), Piece.Color.WHITE);
		verifyColor(Piece.createPiece(Piece.Color.WHITE, new Position("b2"), PAWN), Piece.Color.WHITE);
		verifyColor(Piece.createPiece(Piece.Color.WHITE, new Position("c2"), KNIGHT), Piece.Color.WHITE);
		verifyColor(Piece.createPiece(Piece.Color.WHITE, new Position("d2"), QUEEN), Piece.Color.WHITE);
		verifyColor(Piece.createPiece(Piece.Color.WHITE, new Position("e2"), BISHOP), Piece.Color.WHITE);
		verifyColor(Piece.createPiece(Piece.Color.WHITE, new Position("f2"), ROOK), Piece.Color.WHITE);
	}

	@Test
	@DisplayName("존재하지 않는 기물을 생성할 경우 에러가 발생한다")
	void notExistsPiece() {
		assertThatThrownBy(() -> Piece.createPiece(Piece.Color.NOCOLOR, new Position("a1"), PAWN))
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
		final Piece queen = Piece.createPiece(Piece.Color.WHITE, new Position(queenPosition), QUEEN);
		final Piece king = Piece.createPiece(Piece.Color.WHITE, new Position(kingPosition), KING);
		final Piece pawn = Piece.createPiece(Piece.Color.WHITE, new Position(pawnPosition), PAWN);
		final Piece rook = Piece.createPiece(Piece.Color.WHITE, new Position(rookPosition), ROOK);
		final Piece bishop = Piece.createPiece(Piece.Color.WHITE, new Position(bishopPosition), BISHOP);
		final Piece knight = Piece.createPiece(Piece.Color.WHITE, new Position(knightPosition), KNIGHT);

		board.move(pawnPosition, pawn);

		// when, then
		verifyMoveSameColor(queenPosition, pawnPosition, queen);
		verifyMoveSameColor(kingPosition, pawnPosition, king);
		verifyMoveSameColor(rookPosition, pawnPosition, rook);
		verifyMoveSameColor(bishopPosition, pawnPosition, bishop);
		verifyMoveSameColor(knightPosition, pawnPosition, knight);

	}

	void verifyMoveSameColor(String sourcePosition, String targetPosition, Piece piece) {
		board.move(sourcePosition, piece);

		assertThatThrownBy(() -> chess.movePiece(sourcePosition, targetPosition))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);
	}

	void verifyColor(Piece piece, final Piece.Color color) {
		if (color == Piece.Color.WHITE) {
			assertThat(piece.isWhite()).isTrue();
			assertThat(piece.isBlack()).isFalse();
		}

		if (color == Piece.Color.BLACK) {
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
