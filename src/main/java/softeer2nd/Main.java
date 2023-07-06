package softeer2nd;

import java.util.Scanner;

import softeer2nd.chess.domain.game.Game;
import softeer2nd.chess.view.InputView;
import softeer2nd.chess.view.OutputView;

public class Main {

	public static void main(String[] args) {
		InputView inputView = new InputView(new Scanner(System.in));
		OutputView outputView = new OutputView();
		Game game = new Game(inputView, outputView);

		game.startGame();
	}
}