package softeer2nd.chess.domain.board;

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

	public static Rank initializePawn(Piece.Color color) {
		List<Piece> pawns = new ArrayList<>();
		for (int i = 0; i < MAX_ROW; i++) {
			if (color == Piece.Color.BLACK) {
				pawns.add(Piece.createBlackPawn());
			} else if (color == Piece.Color.WHITE) {
				pawns.add(Piece.createWhitePawn());
			}
		}

		return new Rank(pawns);
	}

	public static Rank initializeBlank() {
		List<Piece> blanks = new ArrayList<>();
		for (int i = 0; i < MAX_ROW; i++) {
			blanks.add(Piece.createBlank());
		}

		return new Rank(blanks);
	}

	public static Rank initializeOtherPieces(Piece.Color color) {
		List<Piece> pieces = new ArrayList<>();
		pieces.add(Piece.createPiece(color, Piece.Type.ROOK));
		pieces.add(Piece.createPiece(color, Piece.Type.KNIGHT));
		pieces.add(Piece.createPiece(color, Piece.Type.BISHOP));
		pieces.add(Piece.createPiece(color, Piece.Type.QUEEN));
		pieces.add(Piece.createPiece(color, Piece.Type.KING));
		pieces.add(Piece.createPiece(color, Piece.Type.BISHOP));
		pieces.add(Piece.createPiece(color, Piece.Type.KNIGHT));
		pieces.add(Piece.createPiece(color, Piece.Type.ROOK));

		return new Rank(pieces);
	}

	public long pieceCount() {
		return row.stream()
			.filter(piece -> piece.getType() != Piece.Type.NO_PIECE)
			.count();
	}

	public String showPieceLine() {
		StringBuilder line = new StringBuilder();
		for (Piece piece : row) {
			line.append(piece.getRepresentation());
		}
		return appendNewLine(line.toString());
	}

	public String showLineWantPiece(Piece.Type type, Piece.Color color) {
		final String line = getLineSamePiece(type, color);
		if (line.equals(BLACK_LINE)) {
			return EMPTY_STRING;
		}
		return appendNewLine(line);
	}

	private String getLineSamePiece(Piece.Type type, Piece.Color color) {
		StringBuilder line = new StringBuilder();
		for (int i = 0; i < MAX_ROW; i++) {
			final Piece piece = row.get(i);
			if (piece.getColor() == color && piece.getType() == type) {
				line.append(piece.getRepresentation());
				continue;
			}
			line.append(Piece.Type.NO_PIECE.getBlackRepresentation());
		}
		return line.toString();
	}
}
