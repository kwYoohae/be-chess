package softeer2nd.chess.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("흰색과 검은색 기물들이 모두 생성 되어야 한다")
    void create_piece() {
        verifyPawn(Piece.createWhitePawn(), Piece.Color.WHITE, Piece.Type.PAWN.getWhiteRepresentation());
        verifyPawn(Piece.createBlackPawn() ,Piece.Color.BLACK, Piece.Type.PAWN.getBlackRepresentation());

        verifyPawn(Piece.createWhiteKnight(), Piece.Color.WHITE, Piece.Type.KNIGHT.getWhiteRepresentation());
        verifyPawn(Piece.createBlackKnight() ,Piece.Color.BLACK, Piece.Type.KNIGHT.getBlackRepresentation());

        verifyPawn(Piece.createWhiteRook(), Piece.Color.WHITE, Piece.Type.ROOK.getWhiteRepresentation());
        verifyPawn(Piece.createBlackRook() ,Piece.Color.BLACK, Piece.Type.ROOK.getBlackRepresentation());

        verifyPawn(Piece.createWhiteBishop(), Piece.Color.WHITE, Piece.Type.BISHOP.getWhiteRepresentation());
        verifyPawn(Piece.createBlackBishop() ,Piece.Color.BLACK, Piece.Type.BISHOP.getBlackRepresentation());

        verifyPawn(Piece.createWhiteQueen(), Piece.Color.WHITE, Piece.Type.QUEEN.getWhiteRepresentation());
        verifyPawn(Piece.createBlackQueen() ,Piece.Color.BLACK, Piece.Type.QUEEN.getBlackRepresentation());

        verifyPawn(Piece.createWhiteKing(), Piece.Color.WHITE, Piece.Type.KING.getWhiteRepresentation());
        verifyPawn(Piece.createBlackKing() ,Piece.Color.BLACK, Piece.Type.KING.getBlackRepresentation());
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

    void verifyPawn(final Piece piece, final Piece.Color color, final char representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }
}
