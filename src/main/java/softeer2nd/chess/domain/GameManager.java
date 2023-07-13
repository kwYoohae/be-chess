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
		Command gameState = Command.EMPTY;
		while (gameState != Command.END) {
			String command = inputView.getUserInput();
			gameState = Command.valueOfInput(command);
			checkCommand(command, gameState);
		}
	}

	private void checkCommand(String command, Command gameState) {
		switch (gameState) {
			case START:
				initializeGame();
				break;
			case MOVE:
				movePiece(command);
				break;
			case END:
				outputView.gameEnd();
				break;
			default:
				outputView.wrongCommand();
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
