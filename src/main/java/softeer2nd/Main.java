package softeer2nd;

import java.util.Scanner;

import softeer2nd.chess.domain.game.Game;
import softeer2nd.chess.view.Command;

public class Main {

	public static void main(String[] args) {
		Command command = new Command(new Scanner(System.in));
		Game game = new Game(command);

		game.startGame();
	}
}