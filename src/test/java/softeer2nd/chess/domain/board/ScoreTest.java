package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;
import static softeer2nd.chess.domain.pieces.Piece.Type.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;
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

		addPiece("b2", Piece.createPiece(Piece.Color.BLACK, new Position("b2"), PAWN));
		addPiece("a7", Piece.createPiece(Piece.Color.BLACK, new Position("a7"), PAWN));
		addPiece("c4", Piece.createPiece(Piece.Color.WHITE, new Position("c4"), PAWN));
		addPiece("h6", Piece.createPiece(Piece.Color.WHITE, new Position("h6"), PAWN));
		addPiece("f6", Piece.createPiece(Piece.Color.WHITE, new Position("f6"), PAWN));

		// when, then
		assertThat(score.calculatePoint(Piece.Color.BLACK)).isEqualTo(2.0);
		assertThat(score.calculatePoint(Piece.Color.WHITE)).isEqualTo(3.0);
	}

	@Test
	@DisplayName("각 기물의 총 점수를 구할 수 있어야한다")
	void calculatePoint() {
		// given
		board.initializeEmpty();

		addPiece("b6", Piece.createPiece(Piece.Color.BLACK, new Position("b6"), PAWN));
		addPiece("e6", Piece.createPiece(Piece.Color.BLACK, new Position("e6"), QUEEN));
		addPiece("b8", Piece.createPiece(Piece.Color.BLACK, new Position("b8"), KING));
		addPiece("c8", Piece.createPiece(Piece.Color.BLACK, new Position("c8"), ROOK));

		addPiece("f2", Piece.createPiece(Piece.Color.WHITE, new Position("f2"), PAWN));
		addPiece("g2", Piece.createPiece(Piece.Color.WHITE, new Position("g2"), PAWN));
		addPiece("e1", Piece.createPiece(Piece.Color.WHITE, new Position("e1"), ROOK));
		addPiece("f1", Piece.createPiece(Piece.Color.WHITE, new Position("f1"), KING));

		// when, then
		assertThat(score.calculatePoint(Piece.Color.BLACK)).isEqualTo(15.0);
		assertThat(score.calculatePoint(Piece.Color.WHITE)).isEqualTo(7.0);

		System.out.println(chessView.showBoard());
	}

	@Test
	@DisplayName("점수를 구할때 폰이 세로줄에 있는 경우는 0.5점을 주고 총점을 계산한다")
	void calculatePawnHalf() {
		// given
		board.initializeEmpty();

		addPiece("b8", Piece.createPiece(Piece.Color.BLACK, new Position("b8"), KING));
		addPiece("c8", Piece.createPiece(Piece.Color.BLACK, new Position("c8"), ROOK));
		addPiece("a7", Piece.createPiece(Piece.Color.BLACK, new Position("a7"), PAWN));
		addPiece("c7", Piece.createPiece(Piece.Color.BLACK, new Position("c7"), PAWN));
		addPiece("d7", Piece.createPiece(Piece.Color.BLACK, new Position("d7"), BISHOP));
		addPiece("b6", Piece.createPiece(Piece.Color.BLACK, new Position("b6"), PAWN));
		addPiece("e6", Piece.createPiece(Piece.Color.BLACK, new Position("e6"), QUEEN));

		addPiece("f4", Piece.createPiece(Piece.Color.WHITE, new Position("f4"), KNIGHT));
		addPiece("g4", Piece.createPiece(Piece.Color.WHITE, new Position("g4"), QUEEN));
		addPiece("f3", Piece.createPiece(Piece.Color.WHITE, new Position("f3"), PAWN));
		addPiece("h3", Piece.createPiece(Piece.Color.WHITE, new Position("h3"), PAWN));
		addPiece("f2", Piece.createPiece(Piece.Color.WHITE, new Position("f2"), PAWN));
		addPiece("g2", Piece.createPiece(Piece.Color.WHITE, new Position("g2"), PAWN));
		addPiece("e1", Piece.createPiece(Piece.Color.WHITE, new Position("e1"), ROOK));
		addPiece("f1", Piece.createPiece(Piece.Color.WHITE, new Position("f1"), KING));

		// when, then
		assertThat(score.calculatePoint(Piece.Color.BLACK)).isEqualTo(20.0);
		assertThat(score.calculatePoint(Piece.Color.WHITE)).isEqualTo(19.5);
	}

	@Test
	@DisplayName("모든 기물이 Collection에 저장되어 있어야 한다")
	void savePieces() {
		// given
		board.initialize();

		// when
		List<Piece> blackPieces = score.getPiecesInBoard(Piece.Color.BLACK);
		List<Piece> whitePieces = score.getPiecesInBoard(Piece.Color.WHITE);

		// then
		assertThat(blackPieces).contains(Piece.createPiece(Piece.Color.BLACK, new Position("b8"), BISHOP),
			Piece.createPiece(Piece.Color.BLACK, new Position("c8"), ROOK),
			Piece.createPiece(Piece.Color.BLACK, new Position("e8"), QUEEN),
			Piece.createPiece(Piece.Color.BLACK, new Position("d8"), KING),
			Piece.createPiece(Piece.Color.BLACK, new Position("f8"), KNIGHT),
			Piece.createPiece(Piece.Color.BLACK, new Position("a8"), PAWN));
		assertThat(whitePieces).contains(
			Piece.createPiece(Piece.Color.WHITE, new Position("b1"), BISHOP),
			Piece.createPiece(Piece.Color.WHITE, new Position("c1"), ROOK),
			Piece.createPiece(Piece.Color.WHITE, new Position("e1"), QUEEN),
			Piece.createPiece(Piece.Color.WHITE, new Position("d1"), KING),
			Piece.createPiece(Piece.Color.WHITE, new Position("f1"), KNIGHT),
			Piece.createPiece(Piece.Color.WHITE, new Position("a1"), PAWN));

	}

	@Test
	@DisplayName("기물이 오름차순으로 정렬될 수 있다.")
	void ascendingCollection() {
		// given
		board.initialize();

		// when
		List<Piece> blackPieces = score.getPiecesInBoard(Piece.Color.BLACK);
		List<Piece> whitePieces = score.getPiecesInBoard(Piece.Color.WHITE);

		score.sortAscendingAllPiece(blackPieces);
		score.sortAscendingAllPiece(whitePieces);

		// then
		assertThat(blackPieces).containsExactly(
			Piece.createPiece(Piece.Color.BLACK, new Position("e8"), KING),
			Piece.createPiece(Piece.Color.BLACK, new Position("a7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("b7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("c7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("d7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("e7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("f7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("g7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("h7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("b8"), KNIGHT),
			Piece.createPiece(Piece.Color.BLACK, new Position("g8"), KNIGHT),
			Piece.createPiece(Piece.Color.BLACK, new Position("c8"), BISHOP),
			Piece.createPiece(Piece.Color.BLACK, new Position("f8"), BISHOP),
			Piece.createPiece(Piece.Color.BLACK, new Position("a8"), ROOK),
			Piece.createPiece(Piece.Color.BLACK, new Position("h8"), ROOK),
			Piece.createPiece(Piece.Color.BLACK, new Position("d8"), QUEEN));

		assertThat(whitePieces).containsExactly(
			Piece.createPiece(Piece.Color.WHITE, new Position("e1"), KING),
			Piece.createPiece(Piece.Color.WHITE, new Position("a2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("b2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("c2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("d2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("e2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("f2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("g2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("h2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("b1"), KNIGHT),
			Piece.createPiece(Piece.Color.WHITE, new Position("g1"), KNIGHT),
			Piece.createPiece(Piece.Color.WHITE, new Position("c1"), BISHOP),
			Piece.createPiece(Piece.Color.WHITE, new Position("f1"), BISHOP),
			Piece.createPiece(Piece.Color.WHITE, new Position("a1"), ROOK),
			Piece.createPiece(Piece.Color.WHITE, new Position("h1"), ROOK),
			Piece.createPiece(Piece.Color.WHITE, new Position("d1"), QUEEN));
	}

	@Test
	@DisplayName("기물이 내림차순으로 정렬될 수 있다.")
	void descendingCollection() {
		// given
		board.initialize();

		// when
		List<Piece> blackPieces = score.getPiecesInBoard(Piece.Color.BLACK);
		List<Piece> whitePieces = score.getPiecesInBoard(Piece.Color.WHITE);

		score.sortDescendingAllPieces(blackPieces);
		score.sortDescendingAllPieces(whitePieces);

		// then
		assertThat(blackPieces).containsExactly(
			Piece.createPiece(Piece.Color.BLACK, new Position("d8"), QUEEN),
			Piece.createPiece(Piece.Color.BLACK, new Position("h8"), ROOK),
			Piece.createPiece(Piece.Color.BLACK, new Position("a8"), ROOK),
			Piece.createPiece(Piece.Color.BLACK, new Position("f8"), BISHOP),
			Piece.createPiece(Piece.Color.BLACK, new Position("c8"), BISHOP),
			Piece.createPiece(Piece.Color.BLACK, new Position("g8"), KNIGHT),
			Piece.createPiece(Piece.Color.BLACK, new Position("b8"), KNIGHT),
			Piece.createPiece(Piece.Color.BLACK, new Position("h7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("g7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("f7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("e7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("d7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("c7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("b7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("a7"), PAWN),
			Piece.createPiece(Piece.Color.BLACK, new Position("e8"), KING));

		assertThat(whitePieces).containsExactly(
			Piece.createPiece(Piece.Color.WHITE, new Position("d1"), QUEEN),
			Piece.createPiece(Piece.Color.WHITE, new Position("h1"), ROOK),
			Piece.createPiece(Piece.Color.WHITE, new Position("a1"), ROOK),
			Piece.createPiece(Piece.Color.WHITE, new Position("f1"), BISHOP),
			Piece.createPiece(Piece.Color.WHITE, new Position("c1"), BISHOP),
			Piece.createPiece(Piece.Color.WHITE, new Position("g1"), KNIGHT),
			Piece.createPiece(Piece.Color.WHITE, new Position("b1"), KNIGHT),
			Piece.createPiece(Piece.Color.WHITE, new Position("h2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("g2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("f2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("e2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("d2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("c2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("b2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("a2"), PAWN),
			Piece.createPiece(Piece.Color.WHITE, new Position("e1"), KING));
	}

	private void addPiece(String position, Piece piece) {
		board.move(position, piece);
	}
}