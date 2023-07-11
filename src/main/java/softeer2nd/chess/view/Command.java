package softeer2nd.chess.view;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Arrays;

public enum Command {
	START("start"), END("end"), MOVE("move");

	private final String command;

	Command(final String command) {
		this.command = command;
	}

	public static Command valueOfInput(String input) {
		return Arrays.stream(values())
			.filter(value -> input.startsWith(value.command))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXECUTE_COMMAND));
	}

	public static void validationCommand(String input) {
		final String[] inputs = input.split(" ");

		validateCommandLength(inputs);
		validateCommand(inputs);
	}

	private static void validateCommandLength(String[] inputs) {
		if (inputs.length != 1 && inputs.length != 3) {
			throw new IllegalArgumentException(NOT_EXECUTE_COMMAND);
		}
	}

	private static void validateCommand(String[] inputs) {
		if (inputs.length == 1 && (inputs[0].equals(START.command) || inputs[0].equals(END.command))) {
			return;
		}
		if (inputs.length == 3 && inputs[0].equals(MOVE.command))
			return;

		throw new IllegalArgumentException(NOT_EXECUTE_COMMAND);
	}
}
