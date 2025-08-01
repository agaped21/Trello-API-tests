package api.tests.board;

import api.clients.BoardClient;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BoardNegativeTests {

    private static final String validBoardId = "6888addd7c47050f613d1cce";
    private static final String invalidBoardId = "6000addd7c47050f613d1cce";
    private BoardClient boardClient;

    @BeforeClass
    public void setup() {
        boardClient = new BoardClient();
    }

    @Test
    public void shouldReturn404ForInvalidBoardId() {
        Response response = boardClient.getBoard(invalidBoardId);
        Assert.assertEquals(response.statusCode(), 404);
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
        Response response = boardClient.getBoardWithCustomToken(validBoardId, "abcd1234");
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void shouldReturn401ForMissingToken() {
        Response response = boardClient.getBoardWithCustomToken(validBoardId, null);
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void shouldReturn401ForInvalidKey() {
        Response response = boardClient.getBoardWithCustomKey(validBoardId, "abcd1234");
        Assert.assertEquals(response.statusCode(), 401);
    }

    @Test
    public void shouldReturn401ForMissingKey() {
        Response response = boardClient.getBoardWithCustomKey(validBoardId, null);
        Assert.assertEquals(response.statusCode(), 401);
    }
}
