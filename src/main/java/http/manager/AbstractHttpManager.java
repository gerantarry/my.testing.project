package http.manager;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.config.RedirectConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

public abstract class AbstractHttpManager {

    public AbstractHttpManager(){
        RestAssured.config()
                .redirect(new RedirectConfig().maxRedirects(10))
                .logConfig(new LogConfig().enableLoggingOfRequestAndResponseIfValidationFails());
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    }

    public abstract Response sendGet(String path);

    public abstract Response sendPost(String path, String body);

    public abstract Response sendPut(String path, String body);

    public abstract Response sendDelete(String body);
}
