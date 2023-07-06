package softeer2nd;

import java.util.Scanner;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.game.Game;
import softeer2nd.chess.view.InputView;
import softeer2nd.chess.view.OutputView;

public class Main {

	public static void main(String[] args) {
		final InputView inputView = new InputView(new Scanner(System.in));
		final OutputView outputView = new OutputView();
		final Board board = new Board();
		Game game = new Game(inputView, outputView, board);

		game.startGame();
	}
}