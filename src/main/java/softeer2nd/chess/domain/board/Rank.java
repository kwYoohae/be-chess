package softeer2nd.chess.domain.board;

import static softeer2nd.chess.domain.pieces.Piece.*;
import static softeer2nd.chess.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.List;

import softeer2nd.chess.domain.pieces.Piece;

public class Rank {

	private static final int MAX_ROW = 8;
	private static final String BLACK_LINE = "........";
	private static final String EMPTY_STRING = "";
	private final List<Piece> row;

	private Rank(List<Piece> row) {
		this.row = row;
	}

	public static Rank initializePawn(Color color) {
		List<Piece> pawns = new ArrayList<>();
		for (int i = 0; i < MAX_ROW; i++) {
			if (color == Color.BLACK) {
				pawns.add(createBlackPawn());
			} else if (color == Color.WHITE) {
				pawns.add(createWhitePawn());
			}
		}

		return new Rank(pawns);
	}

	public static Rank initializeBlank() {
		List<Piece> blanks = new ArrayList<>();
		for (int i = 0; i < MAX_ROW; i++) {
			blanks.add(createBlank());
		}

		return new Rank(blanks);
	}

	public static Rank initializeOtherPieces(Color color) {
		List<Piece> pieces = new ArrayList<>();
		pieces.add(createPiece(color, Type.ROOK));
		pieces.add(createPiece(color, Type.KNIGHT));
		pieces.add(createPiece(color, Type.BISHOP));
		pieces.add(createPiece(color, Type.QUEEN));
		pieces.add(createPiece(color, Type.KING));
		pieces.add(createPiece(color, Type.BISHOP));
		pieces.add(createPiece(color, Type.KNIGHT));
		pieces.add(createPiece(color, Type.ROOK));

		return new Rank(pieces);
	}

	public long pieceCount() {
		return row.stream()
			.filter(piece -> piece.getType() != Type.NO_PIECE)
			.count();
	}

	public String showPieceLine() {
		StringBuilder line = new StringBuilder();
		for (Piece piece : row) {
			line.append(piece.getRepresentation());
		}
		return appendNewLine(line.toString());
	}

	public String showLineWantPiece(Type type, Color color) {
		final String line = getLineSamePiece(type, color);
		if (line.equals(BLACK_LINE)) {
			return EMPTY_STRING;
		}
		return appendNewLine(line);
	}

	private String getLineSamePiece(Type type, Color color) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < MAX_ROW; i++) {
			final Piece piece = row.get(i);
			if (piece.getColor() == color && piece.getType() == type) {
				line.append(piece.getRepresentation());
				continue;
			}
			line.append(Type.NO_PIECE.getBlackRepresentation());
		}
		return line.toString();
	}

	public long pieceCount(final Type type, final Color color) {
		return row.stream()
			.filter(piece -> piece.getType() == type && piece.getColor() == color)
			.count();
	}

	public Piece getPiece(int index) {
		return row.get(index);
	}

	public void move(int index, Piece piece) {
		row.set(index, piece);
	}
}
