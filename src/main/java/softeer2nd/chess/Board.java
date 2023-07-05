package softeer2nd.chess;

import static softeer2nd.utils.StringUtils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import softeer2nd.chess.pieces.Piece;

public class Board {

	private static final String EMPTY_POSITION_REPRESENTATION = ".";
	public static final int BOARD_MAX_INDEX = 8;
	public static final int BOARD_MIN_INDEX = 1;
	public static final String[] WIDTH_ALPHABET = {"A", "B", "C", "D", "E", "F", "G", "H"};

	private final Map<String, Piece> boards = new HashMap<>();

	public void initializePawn(final Piece pawn) {
		String endAlphabet = getPawnColorStartIndex(pawn);

		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			String index = WIDTH_ALPHABET[i] + endAlphabet;
			boards.put(index, pawn);
		}
	}

	private String getPawnColorStartIndex(final Piece piece) {
		if (piece.getColor() == Piece.Color.BLACK)
			return Piece.BLACK_START_LOCATION;
		return Piece.WHITE_START_LOCATION;
	}

	public void initialize() {
		boards.clear();
		initializePawn(Piece.createWhitePawn());
		initializePawn(Piece.createBlackPawn());

		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			initializeOtherPieces(WIDTH_ALPHABET[i]);
		}
	}

	private void initializeOtherPieces(String startIndex) {
		switch (startIndex) {
			case "A":
			case "H":
				boards.put(startIndex + BOARD_MAX_INDEX, Piece.createBlackRook());
				boards.put(startIndex + BOARD_MIN_INDEX, Piece.createWhiteRook());
				break;
			case "B":
			case "G":
				boards.put(startIndex + BOARD_MAX_INDEX, Piece.createBlackKnight());
				boards.put(startIndex + BOARD_MIN_INDEX, Piece.createWhiteKnight());
				break;
			case "C":
			case "F":
				boards.put(startIndex + BOARD_MAX_INDEX, Piece.createBlackBishop());
				boards.put(startIndex + BOARD_MIN_INDEX, Piece.createWhiteBishop());
				break;
			case "D":
				boards.put(startIndex + BOARD_MAX_INDEX, Piece.createBlackQueen());
				boards.put(startIndex + BOARD_MIN_INDEX, Piece.createWhiteQueen());
				break;
			default:
				boards.put(startIndex + BOARD_MAX_INDEX, Piece.createBlackKing());
				boards.put(startIndex + BOARD_MIN_INDEX, Piece.createWhiteKing());
				break;
		}
	}


	public String getPawnsResult(Piece.Color color) {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX; i >= BOARD_MIN_INDEX; i--) {
			final String line = getPawnsLineSameColor(color, i);
			if (line.contains(Piece.WHITE_PAWN_REPRESENTATION) || line.contains(Piece.BLACK_PAWN_REPRESENTATION)) {
				sb.append(appendNewLine(line));
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private String getPawnsLineSameColor(final Piece.Color color, final int endAlphabet) {
		StringBuilder line = new StringBuilder();
		for (int j = 0; j < BOARD_MAX_INDEX; j++) {
			String index = WIDTH_ALPHABET[j] + endAlphabet;
			line.append(getPawnRepresentation(color, boards.get(index)));
		}
		return line.toString();
	}

	private String getPawnRepresentation(final Piece.Color color, final Piece piece) {
		if (piece != null && piece.getColor().equals(color)) {
			return piece.getRepresentation();
		} else {
			return EMPTY_POSITION_REPRESENTATION;
		}
	}

	private String getPawnRepresentation(final Piece piece) {
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
