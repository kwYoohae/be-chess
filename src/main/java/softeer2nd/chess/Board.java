package softeer2nd.chess;

import static softeer2nd.chess.exception.ExceptionMessage.*;
import static softeer2nd.utils.StringUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import softeer2nd.chess.pieces.Piece;

public class Board {

	private static final String EMPTY_LINE_REPRESENTATION = "........";
	private static final char EMPTY_POSITION_REPRESENTATION = '.';
	public static final int BOARD_MAX_INDEX = 8;
	public static final int BOARD_MIN_INDEX = 1;
	public static final String[] WIDTH_ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H"};

	private final Map<String, Piece> boards = new HashMap<>();

	public void add(final Piece piece) {
		String endAlphabet = getStartAlphabet(piece);

		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			String index = WIDTH_ALPHABET[i] + endAlphabet;
			if (!boards.containsKey(index)) {
				boards.put(index, piece);
				break;
			}
		}
	}

	private String getStartAlphabet(final Piece piece) {
		if (piece.getColor().equals(Piece.BLACK_COLOR))
			return Piece.BLACK_START_LOCATION;
		return Piece.WHITE_START_LOCATION;
	}

	public int size() {
		return boards.size();
	}

	public Piece findPawn(final String index) {
		final Piece piece = boards.get(index);
		if (piece == null) {
			throw new IllegalArgumentException(DO_NOT_FIND_PAWN_IN_BOARD);
		}

		return piece;
	}

	public void initialize() {
		boards.clear();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			add(Piece.createWhitePawn());
			add(Piece.createBlackPawn());
		}
	}

	public String getPawnsResult(String color) {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX; i >= BOARD_MIN_INDEX; i--) {
			final String line = getPawnsLineSameColor(color, i);
			if (!Objects.equals(line, EMPTY_LINE_REPRESENTATION)) {
				sb.append(appendNewLine(line));
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private String getPawnsLineSameColor(final String color, final int endAlphabet) {
		StringBuilder line = new StringBuilder();
		for (int j = 0; j < BOARD_MAX_INDEX; j++) {
			String index = WIDTH_ALPHABET[j] + endAlphabet;
			line.append(getPawnRepresentation(color, boards.get(index)));
		}
		return line.toString();
	}

	private char getPawnRepresentation(final String color, final Piece piece) {
		if (piece != null && piece.getColor().equals(color)) {
			return piece.getRepresentation();
		} else {
			return EMPTY_POSITION_REPRESENTATION;
		}
	}

	private char getPawnRepresentation(final Piece piece) {
		if (piece != null) {
			return piece.getRepresentation();
		} else {
			return EMPTY_POSITION_REPRESENTATION;
		}
	}

	public String showBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX; i >= BOARD_MIN_INDEX; i--) {
			sb.append(appendNewLine(getPawnLine(String.valueOf(i))));
		}
		return sb.toString();
	}

	private String getPawnLine(String endAlphabet) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			String index = WIDTH_ALPHABET[i] + endAlphabet;
			line.append(getPawnRepresentation(boards.get(index)));
		}
		return line.toString();
	}

	public long pieceCount() {
		return boards.values().stream()
			.filter((Objects::nonNull))
			.count();
	}
}
