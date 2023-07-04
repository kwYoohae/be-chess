package softeer2nd;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private final List<Pawn> pawns = new ArrayList<>();

	public void add(final Pawn pawn) {
		pawns.add(pawn);
	}

	public int size() {
		return 0;
	}

	public Pawn findPawn(final int i) {
		return null;
	}
}
