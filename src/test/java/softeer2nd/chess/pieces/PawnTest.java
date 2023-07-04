package softeer2nd.chess.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.exception.ExceptionMessage;

class PawnTest {

    @Test
    @DisplayName("흰색과 검은색 폰이 생성되어야 한다")
    void create() {
        verifyPawn(Pawn.WHITE_COLOR, Pawn.WHITE_REPRESENTATION);
        verifyPawn(Pawn.BLACK_COLOR, Pawn.BLACK_REPRESENTATION);
    }

    @Test
    void create_기본생성자() {
        // given
        Pawn pawn = new Pawn();

        // when, then
        assertThat(pawn.getColor()).isEqualTo(Pawn.WHITE_COLOR);
        assertThat(pawn.getRepresentation()).isEqualTo(Pawn.WHITE_REPRESENTATION);
    }

    @Test
    @DisplayName("폰은 검정색과 흰색으로만 이루어져야한다")
    void pawnIsOnlyBlackAndWhite() {
        assertThatThrownBy(() -> new Pawn("yellow"))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.EXCEPTION_MESSAGE_CHESS_COLOR_MUST_BLACK_OR_WHITE);
    }

    void verifyPawn(final String color, final char representation) {
        //given
        final Pawn pawn = new Pawn(color);

        //when, then
        assertThat(pawn.getColor()).isEqualTo(color);
        assertThat(pawn.getRepresentation()).isEqualTo(representation);
    }
}
