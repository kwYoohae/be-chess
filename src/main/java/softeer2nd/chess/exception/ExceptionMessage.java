package softeer2nd.chess.exception;

public final class ExceptionMessage {
	public static final String BOARD_HAS_NOT_OVER_THE_SAVE_PAWN = "저장된 갯수보다 많은 Pawn은 불러올 수 없습니다";
	public static final String EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE = "ExceptionMessage는 인스턴스화 할 수 없는 유틸 클래스입니다";

	private ExceptionMessage() {
		throw new IllegalStateException(EXCEPTION_MESSAGE_CLASS_MUST_NOT_INSTANTIATE);
	}
}
