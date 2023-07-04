package softeer2nd.chess;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import softeer2nd.chess.pieces.Pawn;

public class Board {
	public static final int BOARD_MAX_INDEX = 8;
	public static final int BOARD_MIN_INDEX = 1;
	public static final String[] WIDTH_ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H"};

	private final Map<String, Pawn> boards = new HashMap<>();

	public Board() {
		initialize();
	}

	public void add(final Pawn pawn) {
		String endAlphabet = getStartAlphabet(pawn);

		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			String index = WIDTH_ALPHABET[i] + endAlphabet;
			if (!boards.containsKey(index)) {
				boards.put(index, pawn);
				break;
			}
		}
	}

	private String getStartAlphabet(final Pawn pawn) {
		if (pawn.getColor().equals(Pawn.BLACK_COLOR))
			return Pawn.BLACK_START_LOCATION;
		return Pawn.WHITE_START_LOCATION;
	}

	public int size() {
		return boards.size();
	}

	public Pawn findPawn(final String index) {
		final Pawn pawn = boards.get(index);
		if (pawn == null) {
			throw new IllegalArgumentException(DO_NOT_FIND_PAWN_IN_BOARD);
		}

		return pawn;
	}

	public void initialize() {
		boards.clear();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			add(new Pawn(Pawn.WHITE_COLOR));
			add(new Pawn(Pawn.BLACK_COLOR));
		}
	}

	public String getPawnsResult(String color) {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX; i >= BOARD_MIN_INDEX; i--) {
			sb.append(getPawnsLine(color, i));
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	private String getPawnsLine(final String color, final int endAlphabet) {
		StringBuilder line = new StringBuilder();
		for (int j = 0; j < BOARD_MAX_INDEX; j++) {
			String index = WIDTH_ALPHABET[j] + endAlphabet;
			line.append(getSameColorPawn(color, boards.get(index)));
		}
		if (Objects.equals(line.toString(), "........")) {
			return "";
		}

		line.append('\n');
		return line.toString();
	}

	private char getSameColorPawn(final String color, final Pawn pawn) {
		if (pawn != null && pawn.getColor().equals(color)) {
			return pawn.getRepresentation();
		} else {
			return '.';
		}
	}

	private char getPawnRepresentation(final Pawn pawn) {
		if (pawn != null) {
			return pawn.getRepresentation();
		} else {
			return '.';
		}
	}

	public void print() {
		for (int i = BOARD_MAX_INDEX; i >= BOARD_MIN_INDEX; i--) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < BOARD_MAX_INDEX; j++) {
				String index = WIDTH_ALPHABET[j] + i;
				line.append(getPawnRepresentation(boards.get(index)));
			}
			System.out.println(line);
		}
	}
}
