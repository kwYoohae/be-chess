package softeer2nd.chess.domain;

import softeer2nd.chess.view.ChessView;
import softeer2nd.chess.view.Command;
import softeer2nd.chess.view.InputView;

public class GameManager {

	private final InputView inputView;
	private final ChessView chessView;
	private final Chess chess;

	public GameManager(final InputView inputView, final ChessView chessView, final Chess chess) {
		this.inputView = inputView;
		this.chessView = chessView;
		this.chess = chess;
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
		chess.initializeBoard();
		chessView.printBoard(chess);
	}

	private void movePiece(String command) {
		final String[] commands = command.split(" ");
		String sourcePosition = commands[1];
		String destinationPosition = commands[2];

		chess.movePiece(sourcePosition, destinationPosition);
		chessView.printBoard(chess);
	}
}
