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

	private Map<String, Pawn> boards = new HashMap<>();

	public Board() {
		initialize();
	}

	public void add(final Pawn pawn) {
		String endAlphabet = "2";
		if (pawn.getColor().equals("black"))
			endAlphabet = "7";

		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			String index = WIDTH_ALPHABET[i] + endAlphabet;
			if (!boards.containsKey(index)) {
				boards.put(index, pawn);
				break;
			}
		}
	}

	public int size() {
		int count = 0;
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			for (int j = BOARD_MIN_INDEX; j < BOARD_MAX_INDEX; j++) {
				String index = WIDTH_ALPHABET[i] + j;
				if (boards.containsKey(index))
					count++;
			}
		}
		return count;
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

	public String getWhitePawnsResult() {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MIN_INDEX; i <= BOARD_MAX_INDEX; i++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < BOARD_MAX_INDEX; j++) {
				String index = WIDTH_ALPHABET[j] + i;
				Pawn pawn = boards.get(index);
				if (pawn != null && pawn.getColor().equals(Pawn.WHITE_COLOR)) {
					line.append(pawn.getRepresentation());
				} else {
					line.append(".");
				}
			}
			if (!Objects.equals(line.toString(), "........")) {
				line.append('\n');
				sb.append(line);
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}

	public String getBlackPawnsResult() {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MIN_INDEX; i <= BOARD_MAX_INDEX; i++) {
			StringBuilder line = new StringBuilder();
			for (int j = 0; j < BOARD_MAX_INDEX; j++) {
				String index = WIDTH_ALPHABET[j] + i;
				Pawn pawn = boards.get(index);
				if (pawn != null && pawn.getColor().equals(Pawn.BLACK_COLOR)) {
					line.append(pawn.getRepresentation());
				} else {
					line.append(".");
				}
			}
			if (!Objects.equals(line.toString(), "........")) {
				line.append('\n');
				sb.append(line);
			}
		}
		sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
}
