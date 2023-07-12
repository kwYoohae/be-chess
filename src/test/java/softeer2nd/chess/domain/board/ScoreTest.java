package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.domain.pieces.Piece.Type.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.pieces.Piece;
import softeer2nd.chess.domain.pieces.component.Color;
import softeer2nd.chess.view.ChessView;

class ScoreTest {

	private Board board;
	private Score score;
	private ChessView chessView;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		score = new Score(board);
		chessView = new ChessView(board);
	}

	@Test
	@DisplayName("폰의 점수를 계산할 수 있어야한다")
	void calculatePawnPoint() {
		// given
		board.initializeEmpty();

		board.addPiece("b2", Piece.createPiece(Color.BLACK, PAWN));
		board.addPiece("a7", Piece.createPiece(Color.BLACK, PAWN));
		board.addPiece("c4", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("h6", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("f6", Piece.createPiece(Color.WHITE, PAWN));

		// when, then
		assertThat(score.calculatePoint(Color.BLACK)).isEqualTo(2.0);
		assertThat(score.calculatePoint(Color.WHITE)).isEqualTo(3.0);
	}

	@Test
	@DisplayName("각 기물의 총 점수를 구할 수 있어야한다")
	void calculatePoint() {
		// given
		board.initializeEmpty();

		board.addPiece("b6", Piece.createPiece(Color.BLACK, PAWN));
		board.addPiece("e6", Piece.createPiece(Color.BLACK, QUEEN));
		board.addPiece("b8", Piece.createPiece(Color.BLACK, KING));
		board.addPiece("c8", Piece.createPiece(Color.BLACK, ROOK));

		board.addPiece("f2", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("g2", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("e1", Piece.createPiece(Color.WHITE, ROOK));
		board.addPiece("f1", Piece.createPiece(Color.WHITE, KING));

		// when, then
		assertThat(score.calculatePoint(Color.BLACK)).isEqualTo(15.0);
		assertThat(score.calculatePoint(Color.WHITE)).isEqualTo(7.0);

		System.out.println(chessView.showBoard());
	}

	@Test
	@DisplayName("점수를 구할때 폰이 세로줄에 있는 경우는 0.5점을 주고 총점을 계산한다")
	void calculatePawnHalf() {
		// given
		board.initializeEmpty();

		board.addPiece("b8", Piece.createPiece(Color.BLACK, KING));
		board.addPiece("c8", Piece.createPiece(Color.BLACK, ROOK));
		board.addPiece("a7", Piece.createPiece(Color.BLACK, PAWN));
		board.addPiece("c7", Piece.createPiece(Color.BLACK, PAWN));
		board.addPiece("d7", Piece.createPiece(Color.BLACK, BISHOP));
		board.addPiece("b6", Piece.createPiece(Color.BLACK, PAWN));
		board.addPiece("e6", Piece.createPiece(Color.BLACK, QUEEN));

		board.addPiece("f4", Piece.createPiece(Color.WHITE, KNIGHT));
		board.addPiece("g4", Piece.createPiece(Color.WHITE, QUEEN));
		board.addPiece("f3", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("h3", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("f2", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("g2", Piece.createPiece(Color.WHITE, PAWN));
		board.addPiece("e1", Piece.createPiece(Color.WHITE, ROOK));
		board.addPiece("f1", Piece.createPiece(Color.WHITE, KING));

		// when, then
		assertThat(score.calculatePoint(Color.BLACK)).isEqualTo(20.0);
		assertThat(score.calculatePoint(Color.WHITE)).isEqualTo(19.5);
	}

	@Test
	@DisplayName("모든 기물이 Collection에 저장되어 있어야 한다")
	void savePieces() {
		// given
		board.initialize();

		// when
		List<Piece> blackPieces = score.getPiecesInBoard(Color.BLACK);
		List<Piece> whitePieces = score.getPiecesInBoard(Color.WHITE);

		// then
		assertThat(blackPieces).contains(Piece.createPiece(Color.BLACK, BISHOP),
			Piece.createPiece(Color.BLACK, ROOK),
			Piece.createPiece(Color.BLACK, QUEEN),
			Piece.createPiece(Color.BLACK, KING),
			Piece.createPiece(Color.BLACK, KNIGHT),
			Piece.createPiece(Color.BLACK, PAWN));
		assertThat(whitePieces).contains(
			Piece.createPiece(Color.WHITE, BISHOP),
			Piece.createPiece(Color.WHITE, ROOK),
			Piece.createPiece(Color.WHITE, QUEEN),
			Piece.createPiece(Color.WHITE, KING),
			Piece.createPiece(Color.WHITE, KNIGHT),
			Piece.createPiece(Color.WHITE, PAWN));

	}

	@Test
	@DisplayName("기물이 오름차순으로 정렬될 수 있다.")
	void ascendingCollection() {
		// given
		board.initialize();

		// when
		List<Piece> blackPieces = score.getPiecesInBoard(Color.BLACK);
		List<Piece> whitePieces = score.getPiecesInBoard(Color.WHITE);

		score.sortAscendingAllPiece(blackPieces);
		score.sortAscendingAllPiece(whitePieces);

		// then
		assertThat(blackPieces).containsExactly(
			Piece.createPiece(Color.BLACK, KING),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, KNIGHT),
			Piece.createPiece(Color.BLACK, KNIGHT),
			Piece.createPiece(Color.BLACK, BISHOP),
			Piece.createPiece(Color.BLACK, BISHOP),
			Piece.createPiece(Color.BLACK, ROOK),
			Piece.createPiece(Color.BLACK, ROOK),
			Piece.createPiece(Color.BLACK, QUEEN));

		assertThat(whitePieces).containsExactly(
			Piece.createPiece(Color.WHITE, KING),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, KNIGHT),
			Piece.createPiece(Color.WHITE, KNIGHT),
			Piece.createPiece(Color.WHITE, BISHOP),
			Piece.createPiece(Color.WHITE, BISHOP),
			Piece.createPiece(Color.WHITE, ROOK),
			Piece.createPiece(Color.WHITE, ROOK),
			Piece.createPiece(Color.WHITE, QUEEN));
	}

	@Test
	@DisplayName("기물이 내림차순으로 정렬될 수 있다.")
	void descendingCollection() {
		// given
		board.initialize();

		// when
		List<Piece> blackPieces = score.getPiecesInBoard(Color.BLACK);
		List<Piece> whitePieces = score.getPiecesInBoard(Color.WHITE);

		score.sortDescendingAllPieces(blackPieces);
		score.sortDescendingAllPieces(whitePieces);

		// then
		assertThat(blackPieces).containsExactly(
			Piece.createPiece(Color.BLACK, QUEEN),
			Piece.createPiece(Color.BLACK, ROOK),
			Piece.createPiece(Color.BLACK, ROOK),
			Piece.createPiece(Color.BLACK, BISHOP),
			Piece.createPiece(Color.BLACK, BISHOP),
			Piece.createPiece(Color.BLACK, KNIGHT),
			Piece.createPiece(Color.BLACK, KNIGHT),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, PAWN),
			Piece.createPiece(Color.BLACK, KING));

		assertThat(whitePieces).containsExactly(
			Piece.createPiece(Color.WHITE, QUEEN),
			Piece.createPiece(Color.WHITE, ROOK),
			Piece.createPiece(Color.WHITE, ROOK),
			Piece.createPiece(Color.WHITE, BISHOP),
			Piece.createPiece(Color.WHITE, BISHOP),
			Piece.createPiece(Color.WHITE, KNIGHT),
			Piece.createPiece(Color.WHITE, KNIGHT),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, PAWN),
			Piece.createPiece(Color.WHITE, KING));
	}
}