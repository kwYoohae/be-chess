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
		if (index > size() - 1) {
			throw new IllegalArgumentException("저장된 갯수보다 많은 Pawn은 불러올 수 없습니다");
		}

		return pawns.get(index);
	}
}
