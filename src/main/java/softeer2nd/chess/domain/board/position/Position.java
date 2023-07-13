package softeer2nd.chess.domain.board.position;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Optional;

public class Position {

	private static final int POSITION_COUNT = 2;
	private static final char[] COLUMN = {'1', '2', '3', '4', '5', '6', '7', '8'};

	private final String position;
	private Optional<Integer> cachedX;
	private Optional<Integer> cachedY;

	public Position(final String position) {
		validatePosition(position);
		this.position = position;
		cachedX = Optional.empty();
		cachedY = Optional.empty();
	}

	public int getX() {
		if (cachedX.isEmpty()) {
			final int posX = Row.valueOfPosition(position.charAt(0)).getIndex();
			cachedX = Optional.of(posX);
		}
		return cachedX.get();
	}

	public int getY() {
		if (cachedY.isEmpty()) {
			final int posY = convertToSecondPosition(position.charAt(1));
			cachedY = Optional.of(posY);
		}
		return cachedY.get();
	}

	public String getOrigin() {
		return position;
	}

	private void validatePosition(final String inputs) {
		if (inputs.length() != POSITION_COUNT) {
			throw new IllegalArgumentException(POSITION_INPUT_IS_TO_MANY_OR_LESS_LENGTH);
		}

		if (!Row.checkInRow(inputs.charAt(0)) || !checkColumnInChar(inputs.charAt(1))) {
			throw new IllegalArgumentException(POSITION_INPUT_IS_WRONG);
		}
	}

	private boolean checkColumnInChar(char secondPosition) {
		final int startNumber = Character.getNumericValue(COLUMN[0]);
		final int endNumber = Character.getNumericValue(COLUMN[7]);
		final int positionNumber = Character.getNumericValue(secondPosition);
		return startNumber <= positionNumber && endNumber >= positionNumber;
	}

	private int convertToSecondPosition(char charY) {
		return Character.getNumericValue(charY) - 1;
	}

}
