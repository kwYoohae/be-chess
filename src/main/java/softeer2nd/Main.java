package softeer2nd;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Scanner;

import softeer2nd.chess.Board;

public class Main {

	private static final String ASK_COMMAND_MESSAGE = "명령어를 입력해주세요 : ";
	private static final String START_GAME_MESSAGE = "게임을 시작합니다";
	private static final String END_GAME_MESSAGE = "게임을 종료합니다";
	private static final String START_COMMAND = "start";
	private static final String END_COMMAND = "end";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print(ASK_COMMAND_MESSAGE);
			String command = sc.next();
			if (command.equals(START_COMMAND)) {
				System.out.println(START_GAME_MESSAGE);
				final Board board = new Board();
				board.initialize();
				System.out.println(board.print());
			} else if (command.equals(END_COMMAND)) {
				System.out.println(END_GAME_MESSAGE);
				break;
			} else {
				throw new IllegalArgumentException(NOT_EXECUTE_COMMAND);
			}
		}
	}
}