package softeer2nd.chess.domain.game;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.view.Command;
import softeer2nd.chess.view.InputView;
import softeer2nd.chess.view.ChessView;

public class GameManager {

	private final InputView inputView;
	private final ChessView chessView;
	private final Board board;

	public GameManager(final InputView inputView, final ChessView chessView, final Board board) {
		this.inputView = inputView;
		this.chessView = chessView;
		this.board = board;
	}

	public void startGame() {
		while (true) {
			String command = inputView.getUserInput();
			Command gameState = Command.valueOfInput(command);
			if (gameState == Command.START) {
				initializeGame();
			} else if (gameState == Command.END) {
				chessView.gameEnd();
				break;
			} else if (gameState == Command.MOVE) {
				movePiece(command);
			}
		}
	}

	private void initializeGame() {
		chessView.gameStart();
		board.initialize();
		chessView.printBoard(board);
	}

	private void movePiece(String command) {
		final String[] commands = command.split(" ");
		String sourcePosition = commands[1];
		String destinationPosition = commands[2];

		board.move(sourcePosition, destinationPosition);
		chessView.printBoard(board);
	}
}
