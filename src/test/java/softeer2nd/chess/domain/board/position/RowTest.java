package softeer2nd.chess.domain.board.position;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RowTest {

	@Test
	@DisplayName("존재하지 않은 position의 값을 반환할때 오류가 발생한다")
	void valueOfPosition() {
		// given
		char targetPosition = 'z';

		// when, then
		assertThrows(IllegalArgumentException.class,() -> {
			Row.valueOfPosition(targetPosition);
		});
	}

	@Test
	@DisplayName("존재하지 않은 index의 값을 반환할때 오류가 발생한다")
	void valueOfIndex() {
		// given
		int index = 9;

		// when, then
		assertThrows(IllegalArgumentException.class, () -> {
			Row.valueOfIndex(index);
		});
	}
}