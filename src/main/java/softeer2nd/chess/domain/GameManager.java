package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.view.OutputView;
import softeer2nd.chess.view.Command;
import softeer2nd.chess.view.InputView;

public class GameManager {

	private static final String BLANK = " ";

	private final InputView inputView;
	private final OutputView outputView;
	private final Chess chess;
	private Color turn;

	public GameManager(final InputView inputView, final OutputView outputView, final Chess chess) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.chess = chess;
		turn = Color.WHITE;
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
		chess.initializeBoard();
		turn = Color.WHITE;
		outputView.printBoard(chess, turn);
	}

	private void movePiece(String command) {
		final String[] commands = command.split(BLANK);
		String sourcePosition = commands[1];
		String destinationPosition = commands[2];

		chess.movePiece(sourcePosition, destinationPosition, turn);
		changeTurn();
		outputView.printBoard(chess, turn);
	}

	private void changeTurn() {
		if (turn == Color.WHITE) {
			this.turn = Color.BLACK;
			return;
		}
		this.turn = Color.WHITE;
	}

	public Color getTurn() {
		return turn;
	}
}
