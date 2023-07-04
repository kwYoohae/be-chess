package softeer2nd.chess.exception;

public final class ExceptionMessage {
	public static final String EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE = "ExceptionMessage는 인스턴스화 할 수 없는 유틸 클래스입니다";
	public static final String EXCEPTION_MESSAGE_CHESS_COLOR_MUST_BLACK_OR_WHITE = "체스 기물의 색은 BLACK 또는 WHITE만 존재합니다";
	public static final String DO_NOT_FIND_PAWN_IN_BOARD = "보드에서 Pawn을 찾을 수 없습니다";
	public static final String NOT_EXECUTE_COMMAND = "실행 할 수 없는 명령어 입니다";

	private ExceptionMessage() {
		throw new IllegalStateException(EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE);
	}
}
