package api.tests.board;

import api.clients.BoardClient;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    public void shouldReturn404ForEmptyBoardId() {
        Response response = boardClient.getBoard("");
        assertThat(response.statusCode()).isEqualTo(404);
    }

    @Test
    public void shouldReturn400ForNullBoardId() {
        Response response = boardClient.getBoard(null);
        assertThat(response.statusCode()).isEqualTo(400);
    }

    @Test
    public void shouldReturn401ForInvalidToken() {
        Response response = boardClient.getBoardWithCustomToken(validBoardId, "abcd1234");
        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    public void shouldReturn401ForMissingToken() {
        Response response = boardClient.getBoardWithCustomToken(validBoardId, null);
        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    public void shouldReturn401ForInvalidKey() {
        Response response = boardClient.getBoardWithCustomKey(validBoardId, "abcd1234");
        assertThat(response.statusCode()).isEqualTo(401);
    }

    @Test
    public void shouldReturn401ForMissingKey() {
        Response response = boardClient.getBoardWithCustomKey(validBoardId, null);
        assertThat(response.statusCode()).isEqualTo(401);
    }
}
