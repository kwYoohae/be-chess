package softeer2nd;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HelloTest {

    @Test
    @DisplayName("간단 테스트")
    void hello() {
        int a = 777;
        assertThat(a).isEqualTo(777);
    }
}