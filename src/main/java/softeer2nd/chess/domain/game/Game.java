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
			String command = inputView.getUserInput();
			Command gameState = Command.valueOfInput(command);
			if (gameState == Command.START) {
				initializeGame();
			} else if (gameState == Command.END) {
				outputView.gameEnd();
				break;
			} else if (gameState == Command.MOVE) {
				movePiece(command);
			}
		}
	}

	private void initializeGame() {
		outputView.gameStart();
		board.initialize();
		outputView.printBoard(board);
	}

	private void movePiece(String command) {
		final String[] commands = command.split(" ");
		String sourcePosition = commands[1];
		String destinationPosition = commands[2];

		board.move(sourcePosition, destinationPosition);
		outputView.printBoard(board);
	}
}
