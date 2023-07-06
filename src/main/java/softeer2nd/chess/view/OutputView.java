package softeer2nd.chess.view;

import softeer2nd.chess.domain.board.Board;

public class OutputView {

	private static final String START_GAME_MESSAGE = "게임을 시작합니다";
	private static final String END_GAME_MESSAGE = "게임을 종료합니다";

	public void gameStart() {
		System.out.println(START_GAME_MESSAGE);
	}

	public void printBoard(Board board) {
		System.out.println(board.showBoard());
	}

	public void gameEnd() {
		System.out.println(END_GAME_MESSAGE);
	}
}
