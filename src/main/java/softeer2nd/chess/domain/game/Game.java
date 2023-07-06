package softeer2nd.chess.domain.game;

import static softeer2nd.chess.view.Command.*;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.view.Command;

public class Game {

	private static final String START_GAME_MESSAGE = "게임을 시작합니다";
	private static final String END_GAME_MESSAGE = "게임을 종료합니다";

	private final Command command;

	public Game(final Command command) {
		this.command = command;
	}

	public void startGame() {
		while (true) {
			String input = command.getUserInput();
			if (input.equals(START_COMMAND)) {
				System.out.println(START_GAME_MESSAGE);
				final Board board = new Board();
				board.initialize();
				System.out.println(board.showBoard());
			} else if (input.equals(END_COMMAND)) {
				System.out.println(END_GAME_MESSAGE);
				break;
			}
		}
	}
}