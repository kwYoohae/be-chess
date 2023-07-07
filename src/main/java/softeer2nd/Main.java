package softeer2nd;

import java.util.Scanner;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.game.GameManager;
import softeer2nd.chess.view.InputView;
import softeer2nd.chess.view.ChessView;

public class Main {

	public static void main(String[] args) {
		final InputView inputView = new InputView(new Scanner(System.in));
		final ChessView chessView = new ChessView();
		final Board board = new Board();
		GameManager gameManager = new GameManager(inputView, chessView, board);

		gameManager.startGame();
	}
}