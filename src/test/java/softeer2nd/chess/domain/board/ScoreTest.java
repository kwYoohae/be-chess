package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;

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

		addPiece("b2", Piece.createPawn(Piece.Color.BLACK, new Position("b2")));
		addPiece("a7", Piece.createPawn(Piece.Color.BLACK, new Position("a7")));
		addPiece("c4", Piece.createPawn(Piece.Color.WHITE, new Position("c4")));
		addPiece("h6", Piece.createPawn(Piece.Color.WHITE, new Position("h6")));
		addPiece("f6", Piece.createPawn(Piece.Color.WHITE, new Position("f6")));

		// when, then
		assertThat(score.calculatePoint(Piece.Color.BLACK)).isEqualTo(2.0);
		assertThat(score.calculatePoint(Piece.Color.WHITE)).isEqualTo(3.0);
	}

	@Test
	@DisplayName("각 기물의 총 점수를 구할 수 있어야한다")
	void calculatePoint() {
		// given
		board.initializeEmpty();

		addPiece("b6", Piece.createPawn(Piece.Color.BLACK, new Position("b6")));
		addPiece("e6", Piece.createQueen(Piece.Color.BLACK, new Position("e6")));
		addPiece("b8", Piece.createKing(Piece.Color.BLACK, new Position("b8")));
		addPiece("c8", Piece.createRook(Piece.Color.BLACK, new Position("c8")));

		addPiece("f2", Piece.createPawn(Piece.Color.WHITE, new Position("f2")));
		addPiece("g2", Piece.createPawn(Piece.Color.WHITE, new Position("g2")));
		addPiece("e1", Piece.createRook(Piece.Color.WHITE, new Position("e1")));
		addPiece("f1", Piece.createKing(Piece.Color.WHITE, new Position("f1")));

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

		addPiece("b8", Piece.createKing(Piece.Color.BLACK, new Position("b8")));
		addPiece("c8", Piece.createRook(Piece.Color.BLACK, new Position("c8")));
		addPiece("a7", Piece.createPawn(Piece.Color.BLACK, new Position("a7")));
		addPiece("c7", Piece.createPawn(Piece.Color.BLACK, new Position("c7")));
		addPiece("d7", Piece.createBishop(Piece.Color.BLACK, new Position("d7")));
		addPiece("b6", Piece.createPawn(Piece.Color.BLACK, new Position("b6")));
		addPiece("e6", Piece.createQueen(Piece.Color.BLACK, new Position("e6")));

		addPiece("f4", Piece.createKnight(Piece.Color.WHITE, new Position("f4")));
		addPiece("g4", Piece.createQueen(Piece.Color.WHITE, new Position("g4")));
		addPiece("f3", Piece.createPawn(Piece.Color.WHITE, new Position("f3")));
		addPiece("h3", Piece.createPawn(Piece.Color.WHITE, new Position("h3")));
		addPiece("f2", Piece.createPawn(Piece.Color.WHITE, new Position("f2")));
		addPiece("g2", Piece.createPawn(Piece.Color.WHITE, new Position("g2")));
		addPiece("e1", Piece.createRook(Piece.Color.WHITE, new Position("e1")));
		addPiece("f1", Piece.createKing(Piece.Color.WHITE, new Position("f1")));

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
		assertThat(blackPieces).contains(Piece.createBishop(Piece.Color.BLACK, new Position("b8")),
			Piece.createRook(Piece.Color.BLACK, new Position("c8")),
			Piece.createQueen(Piece.Color.BLACK, new Position("e8")),
			Piece.createKing(Piece.Color.BLACK, new Position("d8")),
			Piece.createKnight(Piece.Color.BLACK, new Position("f8")),
			Piece.createPawn(Piece.Color.BLACK, new Position("a8")));
		assertThat(whitePieces).contains(
			Piece.createBishop(Piece.Color.WHITE, new Position("b1")),
			Piece.createRook(Piece.Color.WHITE, new Position("c1")),
			Piece.createQueen(Piece.Color.WHITE, new Position("e1")),
			Piece.createKing(Piece.Color.WHITE, new Position("d1")),
			Piece.createKnight(Piece.Color.WHITE, new Position("f1")),
			Piece.createPawn(Piece.Color.WHITE, new Position("a1")));

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
			Piece.createKing(Piece.Color.BLACK, new Position("e8")),
			Piece.createPawn(Piece.Color.BLACK, new Position("a7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("b7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("c7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("d7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("e7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("f7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("g7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("h7")),
			Piece.createKnight(Piece.Color.BLACK, new Position("b8")),
			Piece.createKnight(Piece.Color.BLACK, new Position("g8")),
			Piece.createBishop(Piece.Color.BLACK, new Position("c8")),
			Piece.createBishop(Piece.Color.BLACK, new Position("f8")),
			Piece.createRook(Piece.Color.BLACK, new Position("a8")),
			Piece.createRook(Piece.Color.BLACK, new Position("h8")),
			Piece.createQueen(Piece.Color.BLACK, new Position("d8")));

		assertThat(whitePieces).containsExactly(
			Piece.createKing(Piece.Color.WHITE, new Position("e1")),
			Piece.createPawn(Piece.Color.WHITE, new Position("a2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("b2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("c2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("d2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("e2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("f2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("g2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("h2")),
			Piece.createKnight(Piece.Color.WHITE, new Position("b1")),
			Piece.createKnight(Piece.Color.WHITE, new Position("g1")),
			Piece.createBishop(Piece.Color.WHITE, new Position("c1")),
			Piece.createBishop(Piece.Color.WHITE, new Position("f1")),
			Piece.createRook(Piece.Color.WHITE, new Position("a1")),
			Piece.createRook(Piece.Color.WHITE, new Position("h1")),
			Piece.createQueen(Piece.Color.WHITE, new Position("d1")));
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
			Piece.createQueen(Piece.Color.BLACK, new Position("d8")),
			Piece.createRook(Piece.Color.BLACK, new Position("h8")),
			Piece.createRook(Piece.Color.BLACK, new Position("a8")),
			Piece.createBishop(Piece.Color.BLACK, new Position("f8")),
			Piece.createBishop(Piece.Color.BLACK, new Position("c8")),
			Piece.createKnight(Piece.Color.BLACK, new Position("g8")),
			Piece.createKnight(Piece.Color.BLACK, new Position("b8")),
			Piece.createPawn(Piece.Color.BLACK, new Position("h7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("g7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("f7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("e7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("d7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("c7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("b7")),
			Piece.createPawn(Piece.Color.BLACK, new Position("a7")),
			Piece.createKing(Piece.Color.BLACK, new Position("e8")));

		assertThat(whitePieces).containsExactly(
			Piece.createQueen(Piece.Color.WHITE, new Position("d1")),
			Piece.createRook(Piece.Color.WHITE, new Position("h1")),
			Piece.createRook(Piece.Color.WHITE, new Position("a1")),
			Piece.createBishop(Piece.Color.WHITE, new Position("f1")),
			Piece.createBishop(Piece.Color.WHITE, new Position("c1")),
			Piece.createKnight(Piece.Color.WHITE, new Position("g1")),
			Piece.createKnight(Piece.Color.WHITE, new Position("b1")),
			Piece.createPawn(Piece.Color.WHITE, new Position("h2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("g2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("f2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("e2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("d2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("c2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("b2")),
			Piece.createPawn(Piece.Color.WHITE, new Position("a2")),
			Piece.createKing(Piece.Color.WHITE, new Position("e1")));
	}

	private void addPiece(String position, Piece piece) {
		board.move(position, piece);
	}
}