package softeer2nd.chess.domain.game;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.view.Command;
import softeer2nd.chess.view.InputView;

public class Game {

	private static final String START_GAME_MESSAGE = "게임을 시작합니다";
	private static final String END_GAME_MESSAGE = "게임을 종료합니다";

	private final InputView inputView;

	public Game(final InputView inputView) {
		this.inputView = inputView;
	}

	public void startGame() {
		while (true) {
			Command command = inputView.getUserInput();
			if (command == Command.START) {
				System.out.println(START_GAME_MESSAGE);
				final Board board = new Board();
				board.initialize();
				System.out.println(board.showBoard());
			} else if (command == Command.END) {
				System.out.println(END_GAME_MESSAGE);
				break;
			}
		}
	}
}
