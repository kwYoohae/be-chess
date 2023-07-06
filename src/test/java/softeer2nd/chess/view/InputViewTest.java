package softeer2nd.chess.view;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

	@Test
	@DisplayName("만약, 정해져있지 않은 명령어를 입력하면 오류가 발생한다")
	void checkNotDeclareCommand() {
		// given
		String input = "not a command\n";
		final InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputView inputView = new InputView(new Scanner(in));

		// when, then
		Assertions.assertThatThrownBy(inputView::getUserInput)
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(NOT_EXECUTE_COMMAND);

	}
}