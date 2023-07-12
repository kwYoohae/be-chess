package softeer2nd.chess.domain.board;

import static softeer2nd.chess.domain.pieces.Piece.*;
import static softeer2nd.chess.utils.StringUtils.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.board.position.Row;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;

public class Rank {

	private static final String BLACK_PIECE_START_INDEX = "8";
	private static final String WHITE_PIECE_START_INDEX = "1";
	private static final int MAX_ROW = 8;
	private static final String BLANK_LINE = "........";
	private static final String EMPTY_STRING = "";
	private final List<Piece> row;

	private Rank(List<Piece> row) {
		this.row = row;
	}

	public static Rank initializePawn(Color color, int columnIndex) {
		final List<Piece> pawns = IntStream.range(0, MAX_ROW)
			.mapToObj((i) -> {
				String position = Row.valueOfIndex(i).getPosition() + String.valueOf(columnIndex);
				return Piece.createPiece(color, new Position(position), Type.PAWN);
			})
			.collect(Collectors.toList());

		return new Rank(pawns);
	}

	public static Rank initializeBlank(int columnIndex) {
		final List<Piece> blanks = IntStream.range(0, MAX_ROW)
			.mapToObj((i) -> {
				String position = Row.valueOfIndex(i).getPosition() + String.valueOf(columnIndex);
				return Piece.createBlank(new Position(position));
			})
			.collect(Collectors.toList());

		return new Rank(blanks);
	}

	public static Rank initializeOtherPieces(Color color) {
		String startIndex = getStartIndex(color);

		List<Piece> pieces = new ArrayList<>();
		pieces.add(createPiece(color, new Position(Row.A.getPosition() + startIndex), Type.ROOK));
		pieces.add(createPiece(color, new Position(Row.B.getPosition() + startIndex), Type.KNIGHT));
		pieces.add(createPiece(color, new Position(Row.C.getPosition() + startIndex), Type.BISHOP));
		pieces.add(createPiece(color, new Position(Row.D.getPosition() + startIndex), Type.QUEEN));
		pieces.add(createPiece(color, new Position(Row.E.getPosition() + startIndex), Type.KING));
		pieces.add(createPiece(color, new Position(Row.F.getPosition() + startIndex), Type.BISHOP));
		pieces.add(createPiece(color, new Position(Row.G.getPosition() + startIndex), Type.KNIGHT));
		pieces.add(createPiece(color, new Position(Row.H.getPosition() + startIndex), Type.ROOK));

		return new Rank(pieces);
	}

	private static String getStartIndex(final Color color) {
		String startIndex = WHITE_PIECE_START_INDEX;
		if (color == Color.BLACK)
			startIndex = BLACK_PIECE_START_INDEX;
		return startIndex;
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
