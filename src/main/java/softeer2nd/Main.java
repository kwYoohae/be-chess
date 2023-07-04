package softeer2nd;

import static softeer2nd.chess.exception.ExceptionMessage.*;

import java.util.Scanner;

import softeer2nd.chess.Board;

public class Main {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();성
            if (command.equals(START_COMMAND)) {
                final Board board = new Board();
                board.print();
            } else if (command.equals(END_COMMAND)) {
                break;
            } else {
                throw new IllegalArgumentException(NOT_EXECUTE_COMMAND);
            }
        }
    }
}