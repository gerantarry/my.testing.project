package http.manager.v1;

import http.manager.Authorizable;
import http.manager.BaseHttpManager;
import io.restassured.response.Response;

/**
 * This is a httpManager object for
 * <a href="http://restful-booker.herokuapp.com/apidoc/index.html#api-Auth">...</a>
 */
public class HttpManager
        extends BaseHttpManager
        implements Authorizable {
    public HttpManager(){
        super();
    }

    public HttpManager(String baseUri){
        super(baseUri);
    }

    /**
     * Creates a new auth token to use for access to the PUT and DELETE /booking
     * @param path to the system
     * @param body for auth
     * @return response with the token
     */
    @Override
    public Response authorize(String path, String body) {
        return sendPost(path, body);
    }
}
