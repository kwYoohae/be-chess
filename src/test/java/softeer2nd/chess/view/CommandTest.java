package softeer2nd.chess.view;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandTest {

	@Test
	@DisplayName("만약, 정해져있지 않은 명령어를 입력하면 오류가 발생한다")
	void checkNotDeclareCommand() {
		// given
		String input = "not a command\n";

		// when, then
		Assertions.assertEquals(Command.valueOfInput(input), Command.EMPTY);
	}
}