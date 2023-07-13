package softeer2nd.chess.domain.board;

import static softeer2nd.chess.domain.pieces.Piece.*;
import static softeer2nd.chess.utils.StringUtils.*;

import java.util.Collections;
import java.util.List;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;

public class Rank {

	private static final int MAX_ROW = 8;
	private static final String BLANK_LINE = "........";
	private static final String EMPTY_STRING = "";
	private final List<Piece> row;

	public Rank(List<Piece> row) {
		this.row = row;
	}

	public List<Piece> getRow() {
		return Collections.unmodifiableList(row);
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
		if (line.equals(BLANK_LINE)) {
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

	public boolean isPawn(int index, Color color) {
		final Piece piece = row.get(index);
		return piece.getType() == Type.PAWN && piece.getColor() == color;
	}

	public double getScoreExceptPawnInRank(Color color) {
		return row.stream()
			.filter(piece -> piece.getType() != Type.PAWN && piece.getColor() == color)
			.map(piece -> piece.getType().getDefaultPoint())
			.reduce(0.0d, Double::sum);
	}
}
