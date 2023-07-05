package softeer2nd.chess.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("흰색과 검은색 기물들이 모두 생성 되어야 한다")
    void create_piece() {
        verifyPawn(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_PAWN_REPRESENTATION);
        verifyPawn(Piece.createBlackPawn() ,Piece.BLACK_COLOR, Piece.BLACK_PAWN_REPRESENTATION);

        verifyPawn(Piece.createWhiteKnight(), Piece.WHITE_COLOR, Piece.WHITE_KNIGHT_REPRESENTATION);
        verifyPawn(Piece.createBlackKnight() ,Piece.BLACK_COLOR, Piece.BLACK_KNIGHT_REPRESENTATION);

        verifyPawn(Piece.createWhiteRook(), Piece.WHITE_COLOR, Piece.WHITE_ROOK_REPRESENTATION);
        verifyPawn(Piece.createBlackRook() ,Piece.BLACK_COLOR, Piece.BLACK_ROOK_REPRESENTATION);

        verifyPawn(Piece.createWhiteBishop(), Piece.WHITE_COLOR, Piece.WHITE_BISHOP_REPRESENTATION);
        verifyPawn(Piece.createBlackBishop() ,Piece.BLACK_COLOR, Piece.BLACK_BISHOP_REPRESENTATION);

        verifyPawn(Piece.createWhiteQueen(), Piece.WHITE_COLOR, Piece.WHITE_QUEEN_REPRESENTATION);
        verifyPawn(Piece.createBlackQueen() ,Piece.BLACK_COLOR, Piece.BLACK_QUEEN_REPRESENTATION);

        verifyPawn(Piece.createWhiteKing(), Piece.WHITE_COLOR, Piece.WHITE_KING_REPRESENTATION);
        verifyPawn(Piece.createBlackKing() ,Piece.BLACK_COLOR, Piece.BLACK_KING_REPRESENTATION);
    }

    @Test
    @DisplayName("기물들의 색은 생성한 그대로여야 한다")
    void checkColor() {
        verifyColor(Piece.createBlackKing(), Piece.BLACK_COLOR);
        verifyColor(Piece.createBlackPawn(), Piece.BLACK_COLOR);
        verifyColor(Piece.createBlackKnight(), Piece.BLACK_COLOR);
        verifyColor(Piece.createBlackQueen(), Piece.BLACK_COLOR);
        verifyColor(Piece.createBlackBishop(), Piece.BLACK_COLOR);
        verifyColor(Piece.createBlackRook(), Piece.BLACK_COLOR);

        verifyColor(Piece.createWhiteKing(), Piece.WHITE_COLOR);
        verifyColor(Piece.createWhitePawn(), Piece.WHITE_COLOR);
        verifyColor(Piece.createWhiteKnight(), Piece.WHITE_COLOR);
        verifyColor(Piece.createWhiteQueen(), Piece.WHITE_COLOR);
        verifyColor(Piece.createWhiteBishop(), Piece.WHITE_COLOR);
        verifyColor(Piece.createWhiteRook(), Piece.WHITE_COLOR);
    }

    void verifyColor(Piece piece, final String color) {
        if (color.equals(Piece.WHITE_COLOR)) {
            assertThat(piece.isWhite()).isTrue();
            assertThat(piece.isBlack()).isFalse();
        }

        if (color.equals(Piece.BLACK_COLOR)) {
            assertThat(piece.isBlack()).isTrue();
            assertThat(piece.isWhite()).isFalse();
        }
    }

    void verifyPawn(final Piece piece, final String color, final String representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }
}
