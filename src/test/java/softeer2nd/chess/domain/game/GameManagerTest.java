package softeer2nd.chess.domain.game;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.view.InputView;
import softeer2nd.chess.view.ChessView;

class GameManagerTest {

	@Test
	@DisplayName("move를 할 수 있어야 한다")
	void move() {
		// given
		Board board = new Board();
		board.initialize();

		String commnad = appendNewLine("start") + appendNewLine("move b2 b3") + appendNewLine("end");
		InputStream inputStream = new ByteArrayInputStream(commnad.getBytes());
		System.setIn(inputStream);

		OutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		GameManager gameManager = new GameManager(new InputView(new Scanner(inputStream)), new ChessView(), board);

		// when
		gameManager.startGame();

		// then
		assertThat(outputStream.toString())
			.contains(appendNewLine("RNBQKBNR") +
				appendNewLine("PPPPPPPP") +
				appendNewLine("........") +
				appendNewLine("........") +
				appendNewLine("........") +
				appendNewLine(".p......") +
				appendNewLine("p.pppppp") +
				appendNewLine("rnbqkbnr"));
		System.out.println(outputStream);
	}

}