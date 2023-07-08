package softeer2nd.chess.domain.board;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.domain.pieces.Piece;

class ScoreTest {

	private Board board;
	private Score score;

	@BeforeEach
	void beforeEach() {
		board = new Board();
		score = new Score(board);
	}

	@Test
	@DisplayName("폰의 점수를 계산할 수 있어야한다")
	void calculatePawnPoint() {
		// given
		board.initializeEmpty();

		addPiece("b2", Piece.createBlackPawn(new Position("b2")));
		addPiece("a7", Piece.createBlackPawn(new Position("a7")));
		addPiece("c4", Piece.createWhitePawn(new Position("c4")));
		addPiece("h6", Piece.createWhitePawn(new Position("h6")));
		addPiece("f6", Piece.createWhitePawn(new Position("f6")));

		// when, then
		assertThat(score.calculatePoint(Piece.Color.BLACK)).isEqualTo(2.0);
		assertThat(score.calculatePoint(Piece.Color.WHITE)).isEqualTo(3.0);
	}

	@Test
	@DisplayName("각 기물의 총 점수를 구할 수 있어야한다")
	void calculatePoint() {
		// given
		board.initializeEmpty();

		addPiece("b6", Piece.createBlackPawn(new Position("b6")));
		addPiece("e6", Piece.createBlackQueen(new Position("e6")));
		addPiece("b8", Piece.createBlackKing(new Position("b8")));
		addPiece("c8", Piece.createBlackRook(new Position("c8")));

		addPiece("f2", Piece.createWhitePawn(new Position("f2")));
		addPiece("g2", Piece.createWhitePawn(new Position("g2")));
		addPiece("e1", Piece.createWhiteRook(new Position("e1")));
		addPiece("f1", Piece.createWhiteKing(new Position("f1")));

		// when, then
		assertThat(score.calculatePoint(Piece.Color.BLACK)).isEqualTo(15.0);
		assertThat(score.calculatePoint(Piece.Color.WHITE)).isEqualTo(7.0);

		System.out.println(board.showBoard());
	}

	@Test
	@DisplayName("점수를 구할때 폰이 세로줄에 있는 경우는 0.5점을 주고 총점을 계산한다")
	void calculatePawnHalf() {
		// given
		board.initializeEmpty();

		addPiece("b8", Piece.createBlackKing(new Position("b8")));
		addPiece("c8", Piece.createBlackRook(new Position("c8")));
		addPiece("a7", Piece.createBlackPawn(new Position("a7")));
		addPiece("c7", Piece.createBlackPawn(new Position("c7")));
		addPiece("d7", Piece.createBlackBishop(new Position("d7")));
		addPiece("b6", Piece.createBlackPawn(new Position("b6")));
		addPiece("e6", Piece.createBlackQueen(new Position("e6")));

		addPiece("f4", Piece.createWhiteKnight(new Position("f4")));
		addPiece("g4", Piece.createWhiteQueen(new Position("g4")));
		addPiece("f3", Piece.createWhitePawn(new Position("f3")));
		addPiece("h3", Piece.createWhitePawn(new Position("h3")));
		addPiece("f2", Piece.createWhitePawn(new Position("f2")));
		addPiece("g2", Piece.createWhitePawn(new Position("g2")));
		addPiece("e1", Piece.createWhiteRook(new Position("e1")));
		addPiece("f1", Piece.createWhiteKing(new Position("f1")));

		// when, then
		assertThat(score.calculatePoint(Piece.Color.BLACK)).isEqualTo(20.0);
		assertThat(score.calculatePoint(Piece.Color.WHITE)).isEqualTo(19.5);

		System.out.println(board.showBoard());
	}

	private void addPiece(String position, Piece piece) {
		board.move(position, piece);
	}
}