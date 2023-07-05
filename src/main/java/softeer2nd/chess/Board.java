package softeer2nd.chess;

import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;

import softeer2nd.chess.pieces.Piece;

public class Board {

	private static final char EMPTY_POSITION_REPRESENTATION = '.';
	public static final int BOARD_MAX_INDEX = 8;

	private final List<List<Piece>> boards = new ArrayList<>();

	public void initialize() {
		boards.clear();
		boards.add(initializeOtherPieces(Color.WHITE));
		boards.add(initializePawn(Color.WHITE));
		boards.add(initializeBlank());
		boards.add(initializeBlank());
		boards.add(initializeBlank());
		boards.add(initializeBlank());
		boards.add(initializePawn(Color.BLACK));
		boards.add(initializeOtherPieces(Color.BLACK));
	}

	public List<Piece> initializePawn(Color color) {
		List<Piece> pawns = new ArrayList<>();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			if (color == Color.BLACK) {
				pawns.add(Piece.createBlackPawn());
			} else if (color == Color.WHITE) {
				pawns.add(Piece.createWhitePawn());
			}
		}
		return pawns;
	}

	public List<Piece> initializeBlank() {
		List<Piece> blanks = new ArrayList<>();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			blanks.add(Piece.createBlank());
		}

		return blanks;
	}

	private List<Piece> initializeOtherPieces(Color color) {
		List<Piece> pieces = new ArrayList<>();
		pieces.add(Piece.createPiece(color, Type.ROOK));
		pieces.add(Piece.createPiece(color, Type.KNIGHT));
		pieces.add(Piece.createPiece(color, Type.BISHOP));
		pieces.add(Piece.createPiece(color, Type.QUEEN));
		pieces.add(Piece.createPiece(color, Type.KING));
		pieces.add(Piece.createPiece(color, Type.BISHOP));
		pieces.add(Piece.createPiece(color, Type.KNIGHT));
		pieces.add(Piece.createPiece(color, Type.ROOK));
		return pieces;
	}

	public String getPawnsResult(Color color) {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX - 1; i >= 0; i--) {
			final String line = getPawnsLineSameColor(color, i);
			if (line.contains(String.valueOf(Type.PAWN.getWhiteRepresentation())) || line.contains(
				String.valueOf(Type.PAWN.getBlackRepresentation()))) {
				sb.append(appendNewLine(line));
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private String getPawnsLineSameColor(final Color color, final int rowIndex) {
		StringBuilder line = new StringBuilder();
		final List<Piece> rowPieces = boards.get(rowIndex);
		for (int j = 0; j < BOARD_MAX_INDEX; j++) {
			line.append(getPawnRepresentation(color, rowPieces.get(j)));
		}
		return line.toString();
	}

	private char getPawnRepresentation(final Color color, final Piece piece) {
		if (piece != null && color == piece.getColor()) {
			return piece.getRepresentation();
		}
		return EMPTY_POSITION_REPRESENTATION;
	}

	private char getPawnRepresentation(final Piece piece) {
		if (piece != null) {
			return piece.getRepresentation();
		}
		return EMPTY_POSITION_REPRESENTATION;
	}

	public String showBoard() {
		StringBuilder sb = new StringBuilder();
		for (int i = BOARD_MAX_INDEX - 1; i >= 0; i--) {
			sb.append(appendNewLine(getPawnLine(i)));
		}
		return sb.toString();
	}

	private String getPawnLine(final int rowIndex) {
		StringBuilder line = new StringBuilder();
		final List<Piece> rowPieces = boards.get(rowIndex);
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			line.append(getPawnRepresentation(rowPieces.get(i)));
		}
		return line.toString();
	}

	public int pieceCount() {
		int sum = 0;
		for (List<Piece> row : boards) {
			sum += piecesRowCount(row);
		}
		return sum;
	}

	private long piecesRowCount(List<Piece> pieces) {
		return pieces.stream().
			filter(piece -> piece.getType() != Type.NO_PIECE)
			.count();
	}
}
