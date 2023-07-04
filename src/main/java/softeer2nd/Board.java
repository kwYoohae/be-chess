package softeer2nd;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private final List<Pawn> pawns = new ArrayList<>();

	public void add(final Pawn pawn) {
		pawns.add(pawn);
	}

	public int size() {
		return pawns.size();
	}

	public Pawn findPawn(final int index) {
		return pawns.get(index);
	}
}
