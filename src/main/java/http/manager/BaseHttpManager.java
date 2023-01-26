package http.manager;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class BaseHttpManager extends AbstractHttpManager {

    protected final RequestSpecification rqSpec;
    protected final ResponseSpecification rsSpec;

    public BaseHttpManager(){
        this("");
    }
    public BaseHttpManager(String baseUri){
        super();
        rqSpec = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
        rsSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();
    }

    /**
     * for using custom content-type
     * @param contentType for requests
     */
    public void setContentType(String contentType){
        this.rqSpec.contentType(contentType);
    }

    /**
     * for using base url
     * @param url for requests
     */
    public void setBaseUrl(String url){
        this.rqSpec.baseUri(url);
    }

    /**
     * some params will be overwritten
     * @param spec for merging
     */
    public void mergeRequestSpec(RequestSpecification spec){
        rqSpec.spec(spec);
    }

    /**
     * some params will be overwritten
     * @param spec for merging
     */
    public void mergeResponseSpec(ResponseSpecification spec){
        rsSpec.spec(spec);
    }

    /**
     * request with query
     * @param path to resource
     * @param queryParams for filtering
     * @return Response from service
     */
    public Response sendGetWithQuery(String path, Map<String, String> queryParams){
        return given(rqSpec.queryParams(queryParams), rsSpec)
                .get(path);
    }

    @Override
    public Response sendGet(String path) {
        return given(rqSpec, rsSpec)
                .get(path);
    }

    @Override
    public Response sendPost(String path, String body) {
        return given(rqSpec.body(body), rsSpec)
                .post(path);
    }

    @Override
    public Response sendPut(String path, String body) {
        return given(rqSpec.body(body), rsSpec)
                .put(path);
    }

    @Override
    public Response sendDelete(String path) {
        return given(rqSpec, rsSpec)
                .delete(path);
    }
}
