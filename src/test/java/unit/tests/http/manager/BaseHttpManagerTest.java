package unit.tests.http.manager;


import http.manager.BaseHttpManager;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) //позволяет убрать модификатор static с @BeforeAll
public class BaseHttpManagerTest {
    private BaseHttpManager baseHttpManager;
    private final String BASE_URI = "https://jsonplaceholder.typicode.com/";

    @BeforeAll
    public void setUpInstance(){
        baseHttpManager = new BaseHttpManager(BASE_URI);
    }

    @Test
    public void sendGetTest(){
        Response response = baseHttpManager.sendGet("posts/1");
        validateStatusCode(response, 200);
    }

    @Test
    public void sendPostTest(){
        Response response = baseHttpManager.sendPost("posts", "{}");
        validateStatusCode(response, 201);
    }

    @Test
    public void sendPutTest(){
        Response response = baseHttpManager.sendPut("posts/1", "" +
                "{\n" +
                "\"userId\": 1,\n" +
                "\"id\": 1,\n" +
                "\"title\": \"test\",\n" +
                " \"body\": \"sd\"}");
        validateStatusCode(response, 200);
    }

    @Test
    public void sendDeleteTest(){
        Response response = baseHttpManager.sendDelete("posts/1");
        validateStatusCode(response, 200);
    }

    private static void validateStatusCode(Response response, int code) {
        Assertions.assertEquals(code, response.statusCode());
    }

}
