package softeer2nd.chess.domain.board;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Arrays;
import java.util.Optional;

public class Position {

	private static final int POSITION_COUNT = 2;
	private static final String[] ROW = {"a", "b", "c", "d", "e", "f", "g", "h"};
	private static final String[] COLUMN = {"1", "2", "3", "4", "5", "6", "7", "8"};

	private final String x;
	private final String y;
	private Optional<Integer> cachedX;
	private Optional<Integer> cachedY;

	private Position(final String x, final String y) {
		this.x = x;
		this.y = y;
		cachedX = Optional.empty();
		cachedY = Optional.empty();
	}

	public int getX() {
		if (cachedX.isEmpty()) {
			final int posX = convertCharacterToPosition(x.charAt(0));
			cachedX = Optional.of(posX);
		}
		return cachedX.get();
	}

	public int getY() {
		if (cachedY.isEmpty()) {
			final int posY = convertToSecondPosition(Integer.parseInt(y));
			cachedY = Optional.of(posY);
		}
		return cachedY.get();
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
		final boolean isSecondPositionValid = Arrays.stream(COLUMN).anyMatch(column -> column.equals(inputs[1]));
		if (!isFirstPositionValid || !isSecondPositionValid) {
			throw new IllegalArgumentException(POSITION_INPUT_IS_WRONG);
		}
	}

	private int convertCharacterToPosition(char charX) {
		return charX - ROW[0].charAt(0);
	}
	private int convertToSecondPosition(int numberY) {
		return numberY - 1;
	}

}
