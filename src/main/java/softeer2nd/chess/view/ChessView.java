package softeer2nd.chess.view;

import softeer2nd.chess.domain.Chess;

public class ChessView {

	private static final String START_GAME_MESSAGE = "게임을 시작합니다";
	private static final String END_GAME_MESSAGE = "게임을 종료합니다";

	public void gameStart() {
		System.out.println(START_GAME_MESSAGE);
	}

	public void printBoard(Chess chess) {
		System.out.println(chess.showBoard());
	}

	public void gameEnd() {
		System.out.println(END_GAME_MESSAGE);
	}
}
