package softeer2nd;

import java.util.Scanner;

import softeer2nd.chess.domain.game.Game;
import softeer2nd.chess.view.InputView;

public class Main {

	public static void main(String[] args) {
		InputView inputView = new InputView(new Scanner(System.in));
		Game game = new Game(inputView);

		game.startGame();
	}
}