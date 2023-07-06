package softeer2nd.chess.view;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Scanner;

public class InputView {

	private static final String ASK_COMMAND_MESSAGE = "명령어를 입력해주세요 : ";
	public static final String START_COMMAND = "start";
	public static final String END_COMMAND = "end";

	private final Scanner scanner;

	public InputView(final Scanner scanner) {
		this.scanner = scanner;
	}

	public String getUserInput() {
		System.out.print(ASK_COMMAND_MESSAGE);
		String input = scanner.nextLine();
		validateUserInput(input);
		return input;
	}

	private void validateUserInput(String input) {
		if (!input.equals(START_COMMAND) && !input.equals(END_COMMAND)) {
			throw new IllegalArgumentException(NOT_EXECUTE_COMMAND);
		}
	}
}
