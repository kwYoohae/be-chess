package softeer2nd.chess.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceTest {

    @Test
    @DisplayName("흰색과 검은색 폰이 생성되어야 한다")
    void create_piece() {
        verifyPawn(Piece.createWhitePawn(), Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION);
        verifyPawn(Piece.createBlackPawn() ,Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION);
    }

    void verifyPawn(final Piece piece, final String color, final char representation) {
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }
}
