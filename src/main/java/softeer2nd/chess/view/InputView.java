package softeer2nd.chess.view;

import java.util.Scanner;

public class InputView {

	private static final String ASK_COMMAND_MESSAGE = "명령어를 입력해주세요 : ";

	private final Scanner scanner;

	public InputView(final Scanner scanner) {
		this.scanner = scanner;
	}

	public String getUserInput() {
		System.out.print(ASK_COMMAND_MESSAGE);
		return scanner.nextLine();
	}
}
