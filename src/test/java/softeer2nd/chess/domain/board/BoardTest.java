package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.Chess;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.exception.ExceptionMessage;
import softeer2nd.chess.view.ChessView;

class BoardTest {

	private Chess chess;
	private Board board;
	private ChessView chessView;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		chessView = new ChessView(board);
		chess = new Chess(board);
	}

	@Test
	@DisplayName("board에 기물이 제대로 추가 되어야 한다")
	void create() {
		board.initialize();
		assertThat(board.pieceCount()).isEqualTo(32);
		String blankRank = appendNewLine("........");
		assertThat(chessView.showBoard()).isEqualTo(
			appendNewLine("RNBQKBNR") +
				appendNewLine("PPPPPPPP") +
				blankRank + blankRank + blankRank + blankRank +
				appendNewLine("pppppppp") +
				appendNewLine("rnbqkbnr")
		);
	}

	@Test
	void initialize() {
		board.initialize();

		assertThat(board.getPawnsResult(Color.WHITE)).isEqualTo("pppppppp");
		assertThat(board.getPawnsResult(Color.BLACK)).isEqualTo("PPPPPPPP");
	}

	@Test
	@DisplayName("원하는 기물의 갯수를 반환해야 한다")
	void findWantPieceCount() {
		// given
		board.initialize();

		// when
		final long whiteQueen = board.pieceCount(Piece.Type.QUEEN, Color.WHITE);
		final long blackQueen = board.pieceCount(Piece.Type.QUEEN, Color.BLACK);
		final long whiteRook = board.pieceCount(Piece.Type.ROOK, Color.WHITE);
		final long blackRook = board.pieceCount(Piece.Type.ROOK, Color.BLACK);
		final long whiteKing = board.pieceCount(Piece.Type.KING, Color.WHITE);
		final long blackKing = board.pieceCount(Piece.Type.KING, Color.BLACK);
		final long whiteKnight = board.pieceCount(Piece.Type.KNIGHT, Color.WHITE);
		final long blackKnight = board.pieceCount(Piece.Type.KNIGHT, Color.BLACK);
		final long whiteBishop = board.pieceCount(Piece.Type.BISHOP, Color.WHITE);
		final long blackBishop = board.pieceCount(Piece.Type.BISHOP, Color.BLACK);
		final long whitePawn = board.pieceCount(Piece.Type.PAWN, Color.WHITE);
		final long blackPawn = board.pieceCount(Piece.Type.PAWN, Color.BLACK);
		final long blankPiece = board.pieceCount(Piece.Type.NO_PIECE, Color.NOCOLOR);

		// then
		assertThat(whiteQueen).isEqualTo(1);
		assertThat(blackQueen).isEqualTo(1);
		assertThat(whiteRook).isEqualTo(2);
		assertThat(blackRook).isEqualTo(2);
		assertThat(whiteKing).isEqualTo(1);
		assertThat(blackKing).isEqualTo(1);
		assertThat(whiteKnight).isEqualTo(2);
		assertThat(blackKnight).isEqualTo(2);
		assertThat(whiteBishop).isEqualTo(2);
		assertThat(blackBishop).isEqualTo(2);
		assertThat(whitePawn).isEqualTo(8);
		assertThat(blackPawn).isEqualTo(8);
		assertThat(blankPiece).isEqualTo(32);
	}

	@Test
	@DisplayName("원하는 좌표의 기물을 가져올 수 있어야 한다")
	void findPiece() {
		// given
		board.initialize();

		Piece blackRook1 = board.findPiece("a8");
		Piece blackRook2 = board.findPiece("h8");
		Piece whiteRook1 = board.findPiece("a1");
		Piece whiteRook2 = board.findPiece("h1");

		assertThat(blackRook1).isEqualTo(Piece.createPiece(Color.BLACK, Piece.Type.ROOK));
		assertThat(blackRook2).isEqualTo(Piece.createPiece(Color.BLACK, Piece.Type.ROOK));
		assertThat(whiteRook1).isEqualTo(Piece.createPiece(Color.WHITE, Piece.Type.ROOK));
		assertThat(whiteRook2).isEqualTo(Piece.createPiece(Color.WHITE, Piece.Type.ROOK));
	}

	@Test
	@DisplayName("좌표는 제대로 boards를 위한 좌표로 변환되어야 한다")
	void convertPosition() {
		// given
		final Position startPosition = new Position("a1");
		final Position endPosition = new Position("h8");

		// when
		final int startX = startPosition.getX();
		final int startY = startPosition.getY();
		final int endX = endPosition.getX();
		final int endY = endPosition.getY();

		// then
		assertThat(startX).isEqualTo(0);
		assertThat(startY).isEqualTo(0);
		assertThat(endX).isEqualTo(7);
		assertThat(endY).isEqualTo(7);
	}

	@Test
	@DisplayName("임의의 위치에 마음대로 기물을 추가할 수 있어야 한다")
	void createAnywherePiece() {
		board.initializeEmpty();

		String position = "b5";
		final Piece piece = Piece.createPiece(Color.BLACK, Piece.Type.ROOK);
		board.addPiece(position, piece);

		assertThat(board.findPiece(position)).isEqualTo(piece);
		String blankLine = "........";
		assertThat(chessView.showBoard())
			.isEqualTo(appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(".R......") +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine));
	}

	@Test
	@DisplayName("보드를 아무것도 없는 상태로 초기화 할 수 있어야한다")
	void initializeEmpty() {
		board.initializeEmpty();

		String blankLine = "........";
		assertThat(chessView.showBoard())
			.isEqualTo(appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine) +
				appendNewLine(blankLine));
	}

	@Test
	@DisplayName("같은 세로줄에 폰이 있는지 확인할 수 있어야한다")
	void checkColumnSamePawn() {
		// given
		board.initializeEmpty();

		board.addPiece("b2", Piece.createPiece(Color.BLACK, Piece.Type.PAWN));
		board.addPiece("b7", Piece.createPiece(Color.BLACK, Piece.Type.PAWN));
		board.addPiece("c4", Piece.createPiece(Color.WHITE, Piece.Type.PAWN));
		board.addPiece("h6", Piece.createPiece(Color.WHITE, Piece.Type.PAWN));

		// when, then
		assertThat(board.checkSamePawnInColum(Color.BLACK)).isTrue();
		assertThat(board.checkSamePawnInColum(Color.WHITE)).isFalse();

	}

	@Test
	@DisplayName("기물이 다른 위치로도 이동할 수 있어야한다")
	void move() {
		// given
		board.initialize();

		String sourcePosition = "b2";
		String targetPosition = "b3";

		// when
		board.move(sourcePosition, targetPosition);

		// then
		assertThat(Piece.createBlank()).isEqualTo(board.findPiece(sourcePosition));
		assertThat(Piece.createPiece(Color.WHITE, Piece.Type.PAWN)).isEqualTo(
			board.findPiece(targetPosition));
	}

	@Test
	@DisplayName("기물은 자기 자신의 위치로 움직일 수 없습니다")
	void moveSelfPosition() {
		// given
		board.initialize();

		String position = "b2";

		// when, then
		assertThatThrownBy(() -> chess.movePiece(position, position, Color.WHITE))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ExceptionMessage.PIECE_NOT_MOVE_SELF_POSITION);
	}
}
