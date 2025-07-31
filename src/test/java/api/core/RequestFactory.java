package api.core;

import api.utils.Config;
import api.utils.Secrets;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestFactory {

    private static final String trelloKey = Secrets.get("TRELLO_KEY");
    private static final String trelloToken = Secrets.get("TRELLO_TOKEN");
    private static final String trelloBaseUri = Config.get("TRELLO_BASE_URI");

    public static RequestSpecification authenticatedRequest (){
        return new RequestSpecBuilder()
                .setBaseUri(trelloBaseUri)
                .addQueryParam("key", trelloKey)
                .addQueryParam("token", trelloToken)
                .build();
    }

    public static RequestSpecification withCustomToken (String token){
        return new RequestSpecBuilder()
                .setBaseUri(trelloBaseUri)
                .addQueryParam("key", trelloKey)
                .addQueryParam("token", token)
                .build();
    }

    public static RequestSpecification withCustomKey (String key){
        return new RequestSpecBuilder()
                .setBaseUri(trelloBaseUri)
                .addQueryParam("key", key)
                .addQueryParam("token", trelloToken)
                .build();
    }
}
