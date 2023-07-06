package softeer2nd.chess.exception;

public final class ExceptionMessage {
	public static final String EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE = "ExceptionMessage는 인스턴스화 할 수 없는 유틸 클래스입니다";
	public static final String NOT_EXECUTE_COMMAND = "실행 할 수 없는 명령어 입니다";
	public static final String NOT_EXIST_PIECE = "존재하지 않는 기물입니다";
	public static final String POSITION_INPUT_IS_TO_MANY_OR_LESS_LENGTH = "위치의 값을 너무 많이 또는 적게 입력하셨습니다";

	private ExceptionMessage() {
		throw new IllegalStateException(EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE);
	}
}
