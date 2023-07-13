package softeer2nd.chess.view;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.pieces.component.Color;

public class OutputView {

	private static final String START_GAME_MESSAGE = "게임을 시작합니다";
	private static final String END_GAME_MESSAGE = "게임을 종료합니다";
	private static final String TURN_MESSAGE = "현재는 %s의 차례입니다";

	public void gameStart() {
		System.out.println(START_GAME_MESSAGE);
	}

	public void printBoard(Chess chess, Color turn) {
		System.out.println(chess.showBoard());
		System.out.println(String.format(TURN_MESSAGE, turn));
	}

	public void gameEnd() {
		System.out.println(END_GAME_MESSAGE);
	}
}
