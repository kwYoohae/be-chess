package softeer2nd.chess.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.exception.ExceptionMessage;

class PieceTest {

    @Test
    @DisplayName("흰색과 검은색 기물들이 모두 생성 되어야 한다")
    void create_piece() {
        verifyPawn(Piece.createWhitePawn(), Piece.createBlackPawn(), Piece.Type.PAWN);
        verifyPawn(Piece.createWhiteKnight(), Piece.createBlackKnight(), Piece.Type.KNIGHT);
        verifyPawn(Piece.createWhiteRook(), Piece.createBlackRook(), Piece.Type.ROOK);
        verifyPawn(Piece.createWhiteBishop(), Piece.createBlackBishop(), Piece.Type.BISHOP);
        verifyPawn(Piece.createWhiteQueen(), Piece.createBlackQueen(), Piece.Type.QUEEN);
        verifyPawn(Piece.createWhiteKing(), Piece.createBlackKing(), Piece.Type.KING);

        Piece blank = Piece.createBlank();
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.getType()).isEqualTo(Piece.Type.NO_PIECE);
    }

    @Test
    @DisplayName("기물들의 색은 생성한 그대로여야 한다")
    void checkColor() {
        verifyColor(Piece.createBlackKing(), Piece.Color.BLACK);
        verifyColor(Piece.createBlackPawn(), Piece.Color.BLACK);
        verifyColor(Piece.createBlackKnight(), Piece.Color.BLACK);
        verifyColor(Piece.createBlackQueen(), Piece.Color.BLACK);
        verifyColor(Piece.createBlackBishop(), Piece.Color.BLACK);
        verifyColor(Piece.createBlackRook(), Piece.Color.BLACK);

        verifyColor(Piece.createWhiteKing(), Piece.Color.WHITE);
        verifyColor(Piece.createWhitePawn(), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteKnight(), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteQueen(), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteBishop(), Piece.Color.WHITE);
        verifyColor(Piece.createWhiteRook(), Piece.Color.WHITE);
    }

    @Test
    @DisplayName("존재하지 않는 기물을 생성할 경우 에러가 발생한다")
    void notExistsPiece() {
        assertThatThrownBy(() -> Piece.createPiece(Piece.Color.NOCOLOR, Piece.Type.PAWN))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.NOT_EXIST_PIECE);
        assertThatThrownBy(() -> Piece.createPiece(Piece.Color.WHITE, Piece.Type.NO_PIECE))
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
