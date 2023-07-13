package softeer2nd.chess.domain.game;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.GameManager;
import softeer2nd.chess.exception.ExceptionMessage;
import softeer2nd.chess.view.OutputView;
import softeer2nd.chess.view.InputView;

class GameManagerTest {

	private Board board;
	private Chess chess;
	private GameManager gameManager;
	private OutputView outputView = new OutputView();

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chess = new Chess(board);
	}
	@Test
	@DisplayName("move를 할 수 있어야 한다")
	void move() {
		// given
		board.initialize();

		String commnad = appendNewLine("start") + appendNewLine("move b2 b3") + appendNewLine("end");
		InputStream inputStream = new ByteArrayInputStream(commnad.getBytes());
		System.setIn(inputStream);

		OutputStream outputStream = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outputStream));

		gameManager = new GameManager(new InputView(new Scanner(inputStream)), outputView, chess);

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

	@Test
	@DisplayName("자신의 턴에는 자신의 기물만 움질일 수 있다. ")
	void pieceMoveOnlyMyTurn() {
		// given
		String commnad = appendNewLine("start") + appendNewLine("move b7 b5");
		InputStream inputStream = new ByteArrayInputStream(commnad.getBytes());
		System.setIn(System.in);

		gameManager = new GameManager(new InputView(new Scanner(inputStream)), outputView, chess);

		// when
		assertThatThrownBy(() -> gameManager.startGame())
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.USER_MOVABLE_OWN_TURUN);
	}
}