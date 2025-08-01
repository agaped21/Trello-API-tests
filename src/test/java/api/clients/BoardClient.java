package api.clients;

import api.core.RequestFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BoardClient {

    private final String trelloBasePath = "/1/boards/";

    public Response getBoard(String boardId) {
        return given()
                .spec(RequestFactory.authenticatedRequest())
                .basePath(trelloBasePath + boardId)
                .when().get();
    }

    public Response getBoardWithCustomToken(String boardId, String token) {
        return given()
                .spec(RequestFactory.withCustomToken(token))
                .basePath(trelloBasePath + boardId)
                .when().get();
    }

    public Response getBoardWithCustomKey(String boardId, String key) {
        return given()
                .spec(RequestFactory.withCustomKey(key))
                .basePath(trelloBasePath + boardId)
                .when().get();
    }
}
