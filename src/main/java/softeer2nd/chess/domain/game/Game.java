package softeer2nd.chess.domain.game;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.view.Command;
import softeer2nd.chess.view.InputView;
import softeer2nd.chess.view.OutputView;

public class Game {

	private final InputView inputView;
	private final OutputView outputView;
	private final Board board;

	public Game(final InputView inputView, OutputView outputView, Board board) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.board = board;
	}

	public void startGame() {
		while (true) {
			Command command = inputView.getUserInput();
			if (command == Command.START) {
				initializeGame();
			} else if (command == Command.END) {
				outputView.gameEnd();
				break;
			}
		}
	}

	public void initializeGame() {
		outputView.gameStart();
		board.initialize();
		outputView.printBoard(board);
	}
}
