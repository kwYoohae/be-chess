package softeer2nd.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StringUtilsTest {

	@Test
	@DisplayName("appendLine은 제대로 문자끝에 개행을 추가 할 수 있어야한다")
	void appendLine() {
		// given
		String input = "........";
		String answer = "........" + StringUtils.NEWLINE;
		// when, then
		Assertions.assertThat(StringUtils.appendNewLine(input)).isEqualTo(answer);
	}
}
