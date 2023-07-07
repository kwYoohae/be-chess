package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

class PieceTest {

    @Test
    @DisplayName("흰색과 검은색 기물들이 모두 생성 되어야 한다")
    void create_piece() {
        verifyPawn(Piece.createWhitePawn(new Position("a1")), Piece.createBlackPawn(new Position("a2")), Piece.Type.PAWN);
        verifyPawn(Piece.createWhiteKnight(new Position("b1")), Piece.createBlackKnight(new Position("b2")), Piece.Type.KNIGHT);
        verifyPawn(Piece.createWhiteRook(new Position("c1")), Piece.createBlackRook(new Position("c2")), Piece.Type.ROOK);
        verifyPawn(Piece.createWhiteBishop(new Position("d1")), Piece.createBlackBishop(new Position("d2")), Piece.Type.BISHOP);
        verifyPawn(Piece.createWhiteQueen(new Position("e1")), Piece.createBlackQueen(new Position("e2")), Piece.Type.QUEEN);
        verifyPawn(Piece.createWhiteKing(new Position("f1")), Piece.createBlackKing(new Position("f2")), Piece.Type.KING);

        Piece blank = Piece.createBlank(new Position("c3"));
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.getType()).isEqualTo(Piece.Type.NO_PIECE);
    }

    @Test
    @DisplayName("기물들의 색은 생성한 그대로여야 한다")
    void checkColor() {
        verifyColor(Piece.createBlackKing(new Position("a1")), Piece.Color.BLACK);
        verifyColor(Piece.createBlackPawn(new Position("b1")), Piece.Color.BLACK);
        verifyColor(Piece.createBlackKnight(new Position("c1")), Piece.Color.BLACK);
        verifyColor(Piece.createBlackQueen(new Position("d1")), Piece.Color.BLACK);
        verifyColor(Piece.createBlackBishop(new Position("e1")), Piece.Color.BLACK);
        verifyColor(Piece.createBlackRook(new Position("f1")), Piece.Color.BLACK);

        verifyColor(Piece.createWhiteKing(new Position("a2")), Piece.Color.WHITE);
        verifyColor(Piece.createWhitePawn(new Position("b2")), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteKnight(new Position("c2")), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteQueen(new Position("d2")), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteBishop(new Position("e2")), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteRook(new Position("f2")), Piece.Color.WHITE);
    }

    @Test
    @DisplayName("존재하지 않는 기물을 생성할 경우 에러가 발생한다")
    void notExistsPiece() {
        assertThatThrownBy(() -> Piece.createPiece(Piece.Color.NOCOLOR, Piece.Type.PAWN, new Position("a1")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.NOT_EXIST_PIECE);
        assertThatThrownBy(() -> Piece.createPiece(Piece.Color.WHITE, Piece.Type.NO_PIECE, new Position("b1")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.NOT_EXIST_PIECE);
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
