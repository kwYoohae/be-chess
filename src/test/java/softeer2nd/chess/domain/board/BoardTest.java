package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.pieces.Piece;

class BoardTest {

	private Board board;

	@BeforeEach
	void beforeEach() {
		board = new Board();
	}

	@Test
	@DisplayName("board에 기물이 제대로 추가 되어야 한다")
	void create() {
		board.initialize();
		assertThat(board.pieceCount()).isEqualTo(32);
		String blankRank = appendNewLine("........");
		assertThat(board.showBoard()).isEqualTo(
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

		assertThat(board.getPawnsResult(Piece.Color.WHITE)).isEqualTo("pppppppp");
		assertThat(board.getPawnsResult(Piece.Color.BLACK)).isEqualTo("PPPPPPPP");
	}

	@Test
	@DisplayName("원하는 기물의 갯수를 반환해야 한다")
	void findWantPieceCount() {
		// given
		board.initialize();

		// when
		final long whiteQueen = board.pieceCount(Piece.Type.QUEEN, Piece.Color.WHITE);
		final long blackQueen = board.pieceCount(Piece.Type.QUEEN, Piece.Color.BLACK);
		final long whiteRook = board.pieceCount(Piece.Type.ROOK, Piece.Color.WHITE);
		final long blackRook = board.pieceCount(Piece.Type.ROOK, Piece.Color.BLACK);
		final long whiteKing = board.pieceCount(Piece.Type.KING, Piece.Color.WHITE);
		final long blackKing = board.pieceCount(Piece.Type.KING, Piece.Color.BLACK);
		final long whiteKnight = board.pieceCount(Piece.Type.KNIGHT, Piece.Color.WHITE);
		final long blackKnight = board.pieceCount(Piece.Type.KNIGHT, Piece.Color.BLACK);
		final long whiteBishop = board.pieceCount(Piece.Type.BISHOP, Piece.Color.WHITE);
		final long blackBishop = board.pieceCount(Piece.Type.BISHOP, Piece.Color.BLACK);
		final long whitePawn = board.pieceCount(Piece.Type.PAWN, Piece.Color.WHITE);
		final long blackPawn = board.pieceCount(Piece.Type.PAWN, Piece.Color.BLACK);
		final long blankPiece = board.pieceCount(Piece.Type.NO_PIECE, Piece.Color.NOCOLOR);

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

		assertThat(blackRook1).isEqualTo(Piece.createBlackRook());
		assertThat(blackRook2).isEqualTo(Piece.createBlackRook());
		assertThat(whiteRook1).isEqualTo(Piece.createWhiteRook());
		assertThat(whiteRook2).isEqualTo(Piece.createWhiteRook());
	}

	@Test
	@DisplayName("좌표는 제대로 boards를 위한 좌표로 변환되어야 한다")
	void convertPosition() {
		// given
		final Position startPosition = Position.from("a1");
		final Position endPosition = Position.from("h8");

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
		final Piece piece = Piece.createBlackRook();
		addPiece(position, piece);

		assertThat(board.findPiece(position)).isEqualTo(piece);
		String blankLine = "........";
		assertThat(board.showBoard())
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
		assertThat(board.showBoard())
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

		addPiece("b2", Piece.createBlackPawn());
		addPiece("b7", Piece.createBlackPawn());
		addPiece("c4", Piece.createWhitePawn());
		addPiece("h6", Piece.createWhitePawn());

		// when, then
		assertThat(board.checkSamePawnInColum(Piece.Color.BLACK)).isTrue();
		assertThat(board.checkSamePawnInColum(Piece.Color.WHITE)).isFalse();

	}

	@Test
	@DisplayName("폰의 점수를 계산할 수 있어야한다")
	void calculatePawnPoint() {
		// given
		board.initializeEmpty();

		addPiece("b2", Piece.createBlackPawn());
		addPiece("a7", Piece.createBlackPawn());
		addPiece("c4", Piece.createWhitePawn());
		addPiece("h6", Piece.createWhitePawn());
		addPiece("f6", Piece.createWhitePawn());

		// when, then
		assertThat(board.calculatePoint(Piece.Color.BLACK)).isEqualTo(2.0);
		assertThat(board.calculatePoint(Piece.Color.WHITE)).isEqualTo(3.0);
	}

	@Test
	@DisplayName("각 기물의 총 점수를 구할 수 있어야한다")
	void calculatePoint() {
		// given
		board.initializeEmpty();

		addPiece("b6", Piece.createBlackPawn());
		addPiece("e6", Piece.createBlackQueen());
		addPiece("b8", Piece.createBlackKing());
		addPiece("c8", Piece.createBlackRook());

		addPiece("f2", Piece.createWhitePawn());
		addPiece("g2", Piece.createWhitePawn());
		addPiece("e1", Piece.createWhiteRook());
		addPiece("f1", Piece.createWhiteKing());

		// when, then
		assertThat(board.calculatePoint(Piece.Color.BLACK)).isEqualTo(15.0);
		assertThat(board.calculatePoint(Piece.Color.WHITE)).isEqualTo(7.0);

		System.out.println(board.showBoard());
	}

	@Test
	@DisplayName("점수를 구할때 폰이 세로줄에 있는 경우는 0.5점을 주고 총점을 계산한다")
	void calculatePawnHalf() {
		// given
		board.initializeEmpty();

		addPiece("b8", Piece.createBlackKing());
		addPiece("c8", Piece.createBlackRook());
		addPiece("a7", Piece.createBlackPawn());
		addPiece("c7", Piece.createBlackPawn());
		addPiece("d7", Piece.createBlackBishop());
		addPiece("b6", Piece.createBlackPawn());
		addPiece("e6", Piece.createBlackQueen());

		addPiece("f4", Piece.createWhiteKnight());
		addPiece("g4", Piece.createWhiteQueen());
		addPiece("f3", Piece.createWhitePawn());
		addPiece("h3", Piece.createWhitePawn());
		addPiece("f2", Piece.createWhitePawn());
		addPiece("g2", Piece.createWhitePawn());
		addPiece("e1", Piece.createWhiteRook());
		addPiece("f1", Piece.createWhiteKing());

		// when, then
		assertThat(board.calculatePoint(Piece.Color.BLACK)).isEqualTo(20.0);
		assertThat(board.calculatePoint(Piece.Color.WHITE)).isEqualTo(19.5);

		System.out.println(board.showBoard());
	}

	private void addPiece(String position, Piece piece) {
		board.move(position, piece);
	}
}
