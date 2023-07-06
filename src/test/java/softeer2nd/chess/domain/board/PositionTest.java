package softeer2nd.chess.domain.board;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PositionTest {

	@Test
	@DisplayName("만약, position의 범위를 벗어난 위치의 기물을 찾을때 오류가 발생한다")
	void wrongPosition() {
		// given
		String input = "z4";

		// when, then
		Assertions.assertThatThrownBy(() -> Position.from(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(POSITION_INPUT_IS_WRONG);
	}

}