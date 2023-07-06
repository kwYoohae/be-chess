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
		final int whiteQueen = board.pieceCount(Piece.Type.QUEEN, Piece.Color.WHITE);
		final int blackQueen = board.pieceCount(Piece.Type.QUEEN, Piece.Color.BLACK);
		final int whiteRook = board.pieceCount(Piece.Type.ROOK, Piece.Color.WHITE);
		final int blackRook = board.pieceCount(Piece.Type.ROOK, Piece.Color.BLACK);
		final int whiteKing = board.pieceCount(Piece.Type.KING, Piece.Color.WHITE);
		final int blackKing = board.pieceCount(Piece.Type.KING, Piece.Color.BLACK);
		final int whiteKnight = board.pieceCount(Piece.Type.KNIGHT, Piece.Color.WHITE);
		final int blackKnight = board.pieceCount(Piece.Type.KNIGHT, Piece.Color.BLACK);
		final int whiteBishop = board.pieceCount(Piece.Type.BISHOP, Piece.Color.WHITE);
		final int blackBishop = board.pieceCount(Piece.Type.BISHOP, Piece.Color.BLACK);
		final int whitePawn = board.pieceCount(Piece.Type.PAWN, Piece.Color.WHITE);
		final int blackPawn = board.pieceCount(Piece.Type.PAWN, Piece.Color.BLACK);
		final int blankPiece = board.pieceCount(Piece.Type.NO_PIECE, Piece.Color.NOCOLOR);

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
}
