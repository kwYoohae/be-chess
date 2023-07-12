package softeer2nd.chess.domain.board.position;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Arrays;

public enum Row {
	A('a', 0),
	B('b', 1),
	C('c', 2),
	D('d', 3),
	E('e', 4),
	F('f', 5),
	G('g', 6),
	H('h', 7);

	private final char position;
	private final int index;

	Row(final char position, final int index) {
		this.position = position;
		this.index = index;
	}

	public static boolean checkInRow(char position) {
		return Arrays.stream(Row.values())
			.anyMatch(value -> value.position == position);
	}

	public char getPosition() {
		return position;
	}

	public int getIndex() {
		return index;
	}

	public static Row valueOfPosition(char targetPosition) {
		return Arrays.stream(Row.values())
			.filter(value -> value.position == targetPosition)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(POSITION_INPUT_IS_WRONG));
	}

	public static Row valueOfIndex(int columnIndex) {
		return Arrays.stream(Row.values())
			.filter(value -> value.index == columnIndex)
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(POSITION_INPUT_IS_WRONG));
	}
}
