package softeer2nd.chess.view;

import java.util.Arrays;

public enum Command {
	START("start"),
	END("end"),
	MOVE("move"),
	EMPTY("empty");

	private final String command;

	Command(final String command) {
		this.command = command;
	}

	public static Command valueOfInput(String input) {
		if(!validationCommand(input)) {
			return EMPTY;
		}
		return Arrays.stream(values())
			.filter(value -> input.startsWith(value.command))
			.findAny()
			.orElse(EMPTY);
	}

	public static boolean validationCommand(String input) {
		final String[] inputs = input.split(" ");

		if(!validateCommandLength(inputs)) {
			return false;
		}
		return validateCommand(inputs);
	}

	private static boolean validateCommandLength(String[] inputs) {
		if (inputs.length != 1 && inputs.length != 3) {
			return false;
		}
		return true;
	}

	private static boolean validateCommand(String[] inputs) {
		if (inputs.length == 1 && (inputs[0].equals(START.command) || inputs[0].equals(END.command))) {
			return true;
		}
		if (inputs.length == 3 && inputs[0].equals(MOVE.command))
			return true;
		return false;
	}
}
