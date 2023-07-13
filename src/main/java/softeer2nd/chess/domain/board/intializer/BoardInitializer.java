package softeer2nd.chess.domain.board.intializer;

import static softeer2nd.chess.domain.board.Board.*;
import static softeer2nd.chess.domain.pieces.Piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import softeer2nd.chess.domain.board.Rank;
import softeer2nd.chess.domain.board.position.Row;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;

public class BoardInitializer {

	private static final int WHITE_PIECE_START_INDEX = 1;
	private static final int BLACK_PIECE_START_INDEX = 8;
	private static final int BLANK_START_INDEX = 3;
	private static final int BLANK_END_INDEX = 6;
	public void initialize(List<Rank> boards) {
		boards.clear();
		boards.add(initializeOtherPieces(Color.WHITE));
		boards.add(initializePawn(Color.WHITE, WHITE_PIECE_START_INDEX));
		initializeBlanks(boards);
		boards.add(initializePawn(Color.BLACK, BLACK_PIECE_START_INDEX));
		boards.add(initializeOtherPieces(Color.BLACK));
	}

	public void initializeEmpty(List<Rank> boards) {
		boards.clear();
		for (int i = 0; i < BOARD_MAX_INDEX; i++) {
			boards.add(initializeBlank(i + 1));
		}
	}

	public Rank initializePawn(Color color, int columnIndex) {
		final List<Piece> pawns = IntStream.range(0, BOARD_MAX_INDEX)
			.mapToObj((i) -> {
				String position = Row.valueOfIndex(i).getPosition() + String.valueOf(columnIndex);
				return Piece.createPiece(color, Piece.Type.PAWN);
			})
			.collect(Collectors.toList());

		return new Rank(pawns);
	}

	public Rank initializeBlank(int columnIndex) {
		final List<Piece> blanks = IntStream.range(0, BOARD_MAX_INDEX)
			.mapToObj((i) -> {
				String position = Row.valueOfIndex(i).getPosition() + String.valueOf(columnIndex);
				return Piece.createBlank();
			})
			.collect(Collectors.toList());

		return new Rank(blanks);
	}

	public Rank initializeOtherPieces(Color color) {
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

	public void initializeBlanks(List<Rank> ranks) {
		IntStream.range(BLANK_START_INDEX, BLANK_END_INDEX + 1)
			.forEach((index) -> ranks.add(initializeBlank(index)));
	}

}
