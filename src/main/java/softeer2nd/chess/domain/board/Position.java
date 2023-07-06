package softeer2nd.chess.domain.board;

import static softeer2nd.chess.exception.ExceptionMessage.*;

public class Position {

	private static final int POSITION_COUNT = 2;

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
			throw new IllegalArgumentException(POSITION_INPUT_IS_WRONG);
		}
	}

}
