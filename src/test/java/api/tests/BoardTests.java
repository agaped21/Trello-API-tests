package api.tests;

import api.utils.Secrets;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BoardTests {

    private final String boardId = "6888addd7c47050f613d1cce";
    private final String trelloKey = Secrets.get("TRELLO_KEY");
    private final String trelloToken = Secrets.get("TRELLO_TOKEN");
    private Response response;


    @BeforeClass
    public void setup() {
        response = given().queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when().get("https://api.trello.com/1/boards/" + boardId);
    }

    @Test
    public void getBoard_shouldReturn200() {
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    public void getBoard_shouldReturnCorrectNameAndId() {
        String id = response.jsonPath().getString("id");
        String name = response.jsonPath().getString("name");

        Assert.assertEquals(id, boardId);
        Assert.assertEquals(name, "Trello API tests");
    }

    @Test
    public void getBoard_shouldReturnPrefsObject() {
        String prefsVisibility = response.jsonPath().getString("prefs.permissionLevel");
        
        Assert.assertEquals(prefsVisibility, "private");
    }

}
