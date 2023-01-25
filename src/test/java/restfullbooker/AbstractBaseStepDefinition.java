package restfullbooker;

import http.manager.v1.HttpManager;
import io.restassured.response.Response;

public abstract class AbstractBaseStepDefinition {
    protected final HttpManager httpManager = new HttpManager();
    protected Response response;
}
