package api.clients;

import api.utils.Secrets;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BoardClient {

    private final String trelloKey = Secrets.get("TRELLO_KEY");
    private final String trelloToken = Secrets.get("TRELLO_TOKEN");

    public Response getBoard(String boardId) {

        return given()
                .baseUri("https://api.trello.com")
                .basePath("/1/boards/" + boardId)
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when().get();
    }
}
