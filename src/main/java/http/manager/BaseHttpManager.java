package http.manager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseHttpManager extends AbstractHttpManager {

    protected RequestSpecification rqSpec;

    public BaseHttpManager() {
        this("");
    }

    public BaseHttpManager(String baseUri) {
        super();
        rqSpec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .build();
    }

    /**
     * for using custom content-type
     *
     * @param contentType for requests
     */
    public void setContentType(String contentType) {
        rqSpec = rqSpec.contentType(contentType);
    }

    /**
     * for using base url
     *
     * @param url for requests
     */
    public void setBaseUrl(String url) {
        this.rqSpec.baseUri(url);
    }

    /**
     * some params will be overwritten
     *
     * @param spec for merging
     */
    public void mergeRequestSpec(RequestSpecification spec) {
        rqSpec.spec(spec);
    }


    /**
     * request with query
     *
     * @param path        to resource
     * @param queryParams for filtering
     * @return Response from service
     */
    public Response sendGet(String path, Map<String, String> queryParams) {
        queryParams = checkMap(queryParams);
        return given(rqSpec.queryParams(queryParams))
                .get(path);
    }

    @Override
    public Response sendGet(String path) {
        return given(rqSpec).
                when().get(path);
    }

    public Response sendGet(String path, Map<String, String> query, Map<String, String> headers) {
        query = checkMap(query);
        headers = checkMap(headers);

        return given(rqSpec)
                .headers(headers)
                .queryParams(query)
                .when().get(path);
    }

    @Override
    public Response sendPost(String path, String body) {
        return given(rqSpec.body(body))
                .post(path);
    }

    @Override
    public Response sendPut(String path, String body) {
        return given(rqSpec.body(body))
                .put(path);
    }

    @Override
    public Response sendDelete(String path) {
        return given(rqSpec)
                .delete(path);
    }

    private Map<String, String> checkMap(Map<String, String> map){
        if (map == null){
            return new HashMap<>();
        }
        return map;
    }
}
