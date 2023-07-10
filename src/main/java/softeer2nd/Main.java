package softeer2nd;

import java.util.Scanner;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.GameManager;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.view.OutputView;
import softeer2nd.chess.view.InputView;

public class Main {

	public static void main(String[] args) {
		final InputView inputView = new InputView(new Scanner(System.in));
		final OutputView outputView = new OutputView();
		final Board board = new Board();
		final Chess chess = new Chess(board);
		GameManager gameManager = new GameManager(inputView, outputView, chess);

		gameManager.startGame();
	}
}