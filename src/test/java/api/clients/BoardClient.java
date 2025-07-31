package api.clients;

import api.utils.Config;
import api.utils.Secrets;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BoardClient {

    private final String trelloKey = Secrets.get("TRELLO_KEY");
    private final String trelloToken = Secrets.get("TRELLO_TOKEN");
    private final String trelloBaseUri = Config.get("TRELLO_BASE_URI");
    private final String trelloBasePath = "/1/boards/";

    public Response getBoard(String boardId) {

        return given()
                .baseUri(trelloBaseUri)
                .basePath(trelloBasePath + boardId)
                .queryParam("key", trelloKey)
                .queryParam("token", trelloToken)
                .when().get();
    }
}
