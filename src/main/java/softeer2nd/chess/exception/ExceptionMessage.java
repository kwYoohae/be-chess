package softeer2nd.chess.exception;

public final class ExceptionMessage {
	public static final String EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE = "ExceptionMessage는 인스턴스화 할 수 없는 유틸 클래스입니다";
	public static final String NOT_EXECUTE_COMMAND = "실행 할 수 없는 명령어 입니다";
	public static final String NOT_EXIST_PIECE = "존재하지 않는 기물입니다";
	public static final String POSITION_INPUT_IS_TO_MANY_OR_LESS_LENGTH = "위치의 값을 너무 많이 또는 적게 입력하셨습니다";
	public static final String POSITION_INPUT_IS_WRONG = "위치의 값은 a~h + 1~8만 될 수 있습니다 (ex. a8)";
	public static final String PIECE_CAN_NOT_GO_DESTINATION_POSITION = "이 기물은 해당 장소로 갈 수 없습니다";
	public static final String PIECE_CAN_NOT_GO_SAME_COLOR_PIECE = "기물은 같은 팀의 말의 장소로 갈 수 없습니다";
	public static final String BLANK_PIECE_CAN_NOT_MOVE = "기물이 없는곳을 움직일 수는 없습니다";
	public static final String PIECE_NOT_MOVE_SELF_POSITION = "기물은 자기 자신으로는 움직일 수 없습니다";

	private ExceptionMessage() {
		throw new IllegalStateException(EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE);
	}
}
