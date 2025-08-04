package api.tests.board;

import api.clients.BoardClient;
import api.models.Board;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class BoardPositiveTests {

    private static final String validBoardId = "6888addd7c47050f613d1cce";
    private BoardClient boardClient;

    @BeforeClass
    public void setup() {
        boardClient = new BoardClient();
    }

    private Board getBoard() {
        return boardClient.getBoard(validBoardId).as(Board.class);
    }

    @Test
    public void getBoard_shouldReturn200() {
        Response board = boardClient.getBoard(validBoardId);
        assertThat(board.statusCode()).isEqualTo(200);
    }

    @Test
    public void getBoard_shouldReturnCorrectNameAndId() {
        Board board = getBoard();

        assertThat(board.getId()).isEqualTo(validBoardId);
        assertThat(board.getName()).isEqualTo("Trello API tests");
    }

    @Test
    public void getBoard_shouldReturnPrefsObject() {
        Board board = getBoard();

        assertThat(board.getPrefs().getPermissionLevel()).isEqualTo("private");
    }


}
