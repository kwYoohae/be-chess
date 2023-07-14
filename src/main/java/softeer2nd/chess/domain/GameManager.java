package softeer2nd.chess.domain;

import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.view.Command;
import softeer2nd.chess.view.InputView;
import softeer2nd.chess.view.OutputView;

public class GameManager {

	private static final String BLANK = " ";

	private final InputView inputView;
	private final OutputView outputView;
	private final Chess chess;
	private Color turn;
	private boolean isGameStarted;

	public GameManager(final InputView inputView, final OutputView outputView, final Chess chess) {
		this.inputView = inputView;
		this.outputView = outputView;
		this.chess = chess;
		this.turn = Color.WHITE;
		this.isGameStarted = false;
	}

	public void startGame() {
		Command gameState = Command.EMPTY;
		while (gameState != Command.END) {
			String command = inputView.getUserInput();
			gameState = Command.valueOfInput(command);
			checkCommand(command, gameState);
			if (isGameEnd() && gameState == Command.MOVE) {
				break;
			}
		}
		outputView.gameEnd();
	}

	private boolean isGameEnd() {
		return !chess.isKingAlive(turn);
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
		isGameStarted = true;
	}

	private void movePiece(String command) {
		if (!isGameStarted) {
			outputView.printIsNotStartedGame();
			return;
		}

		final String[] commands = command.split(BLANK);
		String sourcePosition = commands[1];
		String destinationPosition = commands[2];

		try	{
			chess.movePiece(sourcePosition, destinationPosition, turn);
			outputView.printBoard(chess, turn);
			changeTurn();
			isChecked();
		} catch (IllegalArgumentException e) {
			outputView.printError(e.getMessage());
		}
	}

	private void changeTurn() {
		if (turn == Color.WHITE) {
			this.turn = Color.BLACK;
			return;
		}
		this.turn = Color.WHITE;
	}

	private void isChecked() {
		if (chess.isChecked(Color.WHITE))
			outputView.printCheck(Color.WHITE);
		if (chess.isChecked(Color.BLACK))
			outputView.printCheck(Color.BLACK);
	}

	public Color getTurn() {
		return turn;
	}
}
