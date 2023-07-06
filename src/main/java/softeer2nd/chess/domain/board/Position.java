package softeer2nd.chess.domain.board;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Arrays;

public class Position {

	private static final int POSITION_COUNT = 2;
	private static final String[] ROW = {"a", "b", "c", "d", "e", "f", "g", "h"};
	private static final String[] COLUMN = {"1", "2", "3", "4", "5", "6", "7", "8"};

	private final String x;
	private final String y;

	private Position(final String x, final String y) {
		this.x = x;
		this.y = y;
	}

	public static Position from(final String input) {
		final String[] split = input.split("");
		validatePosition(split);
		return new Position(split[0], split[1]);
	}

	private static void validatePosition(final String[] inputs) {
		if (inputs.length != POSITION_COUNT) {
			throw new IllegalArgumentException(POSITION_INPUT_IS_TO_MANY_OR_LESS_LENGTH);
		}

		final boolean isFirstPositionValid = Arrays.stream(ROW).anyMatch(row -> row.equals(inputs[0]));
		final boolean isSecondPositionValid = Arrays.stream(COLUMN).anyMatch(column -> column.equals(inputs[0]));
		if (!isFirstPositionValid || !isSecondPositionValid) {
			throw new IllegalArgumentException(POSITION_INPUT_IS_WRONG);
		}
	}

}
