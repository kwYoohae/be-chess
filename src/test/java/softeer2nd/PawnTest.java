package softeer2nd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @Test
    @DisplayName("흰색과 검은색 폰이 생성되어야 한다")
    public void create() {
        //given
        final String white = "white";
        final String black = "black";

        final Pawn whitePawn = new Pawn(white);
        final Pawn blackPawn = new Pawn(black);

        //when, then
        assertThat(whitePawn.getColor()).isEqualTo(white);
        assertThat(blackPawn.getColor()).isEqualTo(black);
    }
}
