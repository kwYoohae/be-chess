package softeer2nd.chess.view;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Arrays;

public enum Command {
	START("start"), END("end");

	private final String command;

	Command(final String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public static Command valueOfInput(String input) {
		return Arrays.stream(values())
			.filter(value -> value.command.equals(input))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXECUTE_COMMAND));
	}
}
