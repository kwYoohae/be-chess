package softeer2nd.chess.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.exception.ExceptionMessage;

class PieceTest {

    @Test
    @DisplayName("흰색과 검은색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Piece.WHITE_COLOR, Piece.WHITE_REPRESENTATION);
        verifyPawn(Piece.BLACK_COLOR, Piece.BLACK_REPRESENTATION);
    }

    @Test
    void create_기본생성자() {
        // given
        Piece piece = new Piece();

        // when, then
        assertThat(piece.getColor()).isEqualTo(Piece.WHITE_COLOR);
        assertThat(piece.getRepresentation()).isEqualTo(Piece.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("폰은 검정색과 흰색으로만 이루어져야한다")
    void pawnIsOnlyBlackAndWhite() {
        assertThatThrownBy(() -> new Piece("yellow"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.EXCEPTION_MESSAGE_CHESS_COLOR_MUST_BLACK_OR_WHITE);
    }

    void verifyPawn(final String color, final char representation) {
        //given
        final Piece piece = new Piece(color);

        //when, then
        assertThat(piece.getColor()).isEqualTo(color);
        assertThat(piece.getRepresentation()).isEqualTo(representation);
    }
}
