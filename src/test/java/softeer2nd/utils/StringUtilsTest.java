package softeer2nd.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.utils.StringUtils;

public class StringUtilsTest {

	@Test
	@DisplayName("appendLine은 제대로 문자끝에 개행을 추가 할 수 있어야한다")
	void appendLine() {
		// given
		String input = "........";
		String answer = "........\n";
		// when, then
		Assertions.assertThat(StringUtils.appendLine(input)).isEqualTo(input + System.getProperty(answer));
	}
}
