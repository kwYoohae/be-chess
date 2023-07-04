package softeer2nd.chess;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.ArrayList;
import java.util.List;

import softeer2nd.chess.pieces.Pawn;

public class Board {

	private final List<Pawn> pawns = new ArrayList<>();

	public Board() {
		initialize();
	}

	public void add(final Pawn pawn) {
		pawns.add(pawn);
	}

	public int size() {
		return pawns.size();
	}

	public Pawn findPawn(final int index) {
		if (index > size() - 1) {
			throw new IllegalArgumentException(BOARD_HAS_NOT_OVER_THE_SAVE_PAWN);
		}

		return pawns.get(index);
	}

	public void initialize() {

	}

	public String getWhitePawnsResult() {
		return "";
	}

	public String getBlackPawnsResult() {
		return "";
	}
}
