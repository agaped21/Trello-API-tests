package api.tests;

import api.clients.BoardClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class BoardTests {

    private BoardClient boardClient;

    @BeforeClass
    public void setup() {
        boardClient = new BoardClient();
    }

    @Test
    public void getBoard_shouldReturn200() {
        Response board = boardClient.getBoard("6888addd7c47050f613d1cce");
        Assert.assertEquals(board.statusCode(), 200);
    }

    @Test
    public void getBoard_shouldReturnCorrectNameAndId() {
        Response board = boardClient.getBoard("6888addd7c47050f613d1cce");

        String id = board.jsonPath().getString("id");
        String name = board.jsonPath().getString("name");

        Assert.assertEquals(id, "6888addd7c47050f613d1cce");
        Assert.assertEquals(name, "Trello API tests");
    }

    @Test
    public void getBoard_shouldReturnPrefsObject() {
        Response board = boardClient.getBoard("6888addd7c47050f613d1cce");
        String prefsVisibility = board.jsonPath().getString("prefs.permissionLevel");

        Assert.assertEquals(prefsVisibility, "private");
    }

    @Test
    public void shouldReturn400ForInvalidBoardId() {
        Response response = boardClient.getBoard("fakeBoardId123");
        Assert.assertEquals(response.statusCode(), 400);
    }

    @Test
    public void shouldReturn404ForEmptyBoardId() {
        Response response = boardClient.getBoard("");
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test
    public void shouldReturn400ForNullBoardId() {
        Response response = boardClient.getBoard(null);
        Assert.assertEquals(response.statusCode(), 400);
    }

    @Test
    public void shouldReturn401ForInvalidToken() {
        Response response = boardClient.getBoardWithToken("6888addd7c47050f613d1cce", "abcd1234");
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void shouldReturn401ForMissingToken() {
        Response response = boardClient.getBoardWithToken("6888addd7c47050f613d1cce", null);
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void shouldReturn401ForInvalidKey() {
        Response response = boardClient.getBoardWithKey("6888addd7c47050f613d1cce", "abcd1234");
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void shouldReturn401ForMissingKey() {
        Response response = boardClient.getBoardWithKey("6888addd7c47050f613d1cce", null);
        Assert.assertEquals(response.statusCode(), 401);
    }
}
