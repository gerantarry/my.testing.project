package http.manager;

import io.restassured.response.Response;

public interface Authorizable {
    public Response authorize(String path, String body);
}
