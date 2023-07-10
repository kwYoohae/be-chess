package softeer2nd.chess.domain.pieces;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import softeer2nd.chess.domain.board.Board;
import softeer2nd.chess.domain.board.position.Position;
import softeer2nd.chess.exception.ExceptionMessage;

class PieceTest {

    private Board board;

    @BeforeEach
    void beforeEach() {
        board = new Board();
    }

    @Test
    @DisplayName("흰색과 검은색 기물들이 모두 생성 되어야 한다")
    void create_piece() {
        verifyPawn(Piece.createPawn(Piece.Color.WHITE, new Position("a1")), Piece.createPawn(Piece.Color.BLACK, new Position("a2")), Piece.Type.PAWN);
        verifyPawn(Piece.createKnight(Piece.Color.WHITE, new Position("b1")), Piece.createKnight(Piece.Color.BLACK, new Position("b2")), Piece.Type.KNIGHT);
        verifyPawn(Piece.createRook(Piece.Color.WHITE, new Position("c1")), Piece.createRook(Piece.Color.BLACK, new Position("c2")), Piece.Type.ROOK);
        verifyPawn(Piece.createBishop(Piece.Color.WHITE, new Position("d1")), Piece.createBishop(Piece.Color.BLACK, new Position("d2")), Piece.Type.BISHOP);
        verifyPawn(Piece.createQueen(Piece.Color.WHITE, new Position("e1")), Piece.createQueen(Piece.Color.BLACK, new Position("e2")), Piece.Type.QUEEN);
        verifyPawn(Piece.createKing(Piece.Color.WHITE, new Position("f1")), Piece.createKing(Piece.Color.BLACK, new Position("f2")), Piece.Type.KING);

        Piece blank = Piece.createBlank(new Position("c3"));
        assertThat(blank.isBlack()).isFalse();
        assertThat(blank.isWhite()).isFalse();
        assertThat(blank.getType()).isEqualTo(Piece.Type.NO_PIECE);
    }

    @Test
    @DisplayName("기물들의 색은 생성한 그대로여야 한다")
    void checkColor() {
        verifyColor(Piece.createKing(Piece.Color.BLACK, new Position("a1")), Piece.Color.BLACK);
        verifyColor(Piece.createPawn(Piece.Color.BLACK, new Position("b1")), Piece.Color.BLACK);
        verifyColor(Piece.createKnight(Piece.Color.BLACK, new Position("c1")), Piece.Color.BLACK);
        verifyColor(Piece.createQueen(Piece.Color.BLACK, new Position("d1")), Piece.Color.BLACK);
        verifyColor(Piece.createBishop(Piece.Color.BLACK, new Position("e1")), Piece.Color.BLACK);
        verifyColor(Piece.createRook(Piece.Color.BLACK, new Position("f1")), Piece.Color.BLACK);

        verifyColor(Piece.createKing(Piece.Color.WHITE, new Position("a2")), Piece.Color.WHITE);
        verifyColor(Piece.createPawn(Piece.Color.WHITE, new Position("b2")), Piece.Color.WHITE);
        verifyColor(Piece.createKnight(Piece.Color.WHITE, new Position("c2")), Piece.Color.WHITE);
        verifyColor(Piece.createQueen(Piece.Color.WHITE, new Position("d2")), Piece.Color.WHITE);
        verifyColor(Piece.createBishop(Piece.Color.WHITE, new Position("e2")), Piece.Color.WHITE);
        verifyColor(Piece.createRook(Piece.Color.WHITE, new Position("f2")), Piece.Color.WHITE);
    }

    @Test
    @DisplayName("존재하지 않는 기물을 생성할 경우 에러가 발생한다")
    void notExistsPiece() {
        assertThatThrownBy(() -> Piece.createPawn(Piece.Color.NOCOLOR, new Position("a1")))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.NOT_EXIST_PIECE);
    }

    @Test
    @DisplayName("자기팀의 기물로는 이동할 수 없습니다")
    void canNotGoSameTeam() {
        // given
        board.initializeEmpty();

        String queenPosition = "d4";
        String pawnPosition = "d5";
        String kingPosition = "d6";
        String rookPosition = "c5";
        final Piece queen = Piece.createQueen(Piece.Color.WHITE, new Position(queenPosition));
        final Piece king = Piece.createKing(Piece.Color.WHITE, new Position(kingPosition));
        final Piece pawn = Piece.createPawn(Piece.Color.WHITE, new Position(pawnPosition));
        final Piece rook = Piece.createRook(Piece.Color.WHITE, new Position(rookPosition));
        board.move(queenPosition, queen);
        board.move(pawnPosition, pawn);
        board.move(kingPosition, king);
        board.move(rookPosition, rook);

        // when
        assertThatThrownBy(() -> board.move(kingPosition, pawnPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);

        assertThatThrownBy(() -> board.move(queenPosition, pawnPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);

        assertThatThrownBy(() -> board.move(kingPosition, pawnPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);

        assertThatThrownBy(() -> board.move(rookPosition, pawnPosition))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage(ExceptionMessage.PIECE_CAN_NOT_GO_SAME_COLOR_PIECE);

    }

    void verifyColor(Piece piece, final Piece.Color color) {
        if (color == Piece.Color.WHITE) {
            assertThat(piece.isWhite()).isTrue();
            assertThat(piece.isBlack()).isFalse();
        }

        if (color == Piece.Color.BLACK) {
            assertThat(piece.isBlack()).isTrue();
            assertThat(piece.isWhite()).isFalse();
        }
    }

    void verifyPawn(final Piece whitePiece, final Piece blackPiece, final Piece.Type type) {
        assertThat(whitePiece.isWhite()).isTrue();
        assertThat(whitePiece.getType()).isEqualTo(type);

        assertThat(blackPiece.isBlack()).isTrue();
        assertThat(blackPiece.getType()).isEqualTo(type);
    }
}
