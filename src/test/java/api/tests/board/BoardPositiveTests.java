package api.tests.board;

import api.clients.BoardClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class BoardPositiveTests {

    private static final String validBoardId = "6888addd7c47050f613d1cce";
    private BoardClient boardClient;

    @BeforeClass
    public void setup() {
        boardClient = new BoardClient();
    }

    @Test
    public void getBoard_shouldReturn200() {
        Response board = boardClient.getBoard(validBoardId);
        Assert.assertEquals(board.statusCode(), 200);
    }

    @Test
    public void getBoard_shouldReturnCorrectNameAndId() {
        Response board = boardClient.getBoard(validBoardId);

        String id = board.jsonPath().getString("id");
        String name = board.jsonPath().getString("name");

        Assert.assertEquals(id, validBoardId);
        Assert.assertEquals(name, "Trello API tests");
    }

    @Test
    public void getBoard_shouldReturnPrefsObject() {
        Response board = boardClient.getBoard(validBoardId);
        String prefsVisibility = board.jsonPath().getString("prefs.permissionLevel");

        Assert.assertEquals(prefsVisibility, "private");
    }
}
