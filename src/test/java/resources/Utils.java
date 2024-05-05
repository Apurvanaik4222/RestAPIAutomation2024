package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification baseReq;

    public  static String rawToJson(String key, Response response){
        JsonPath jsonPath =new JsonPath(response.asString());
       return jsonPath.get(key);
    }


    public RequestSpecification requestSpecification() throws IOException {

        if(baseReq==null) {
            PrintStream stream = new PrintStream(new FileOutputStream("logs.txt"));

            baseReq = new RequestSpecBuilder().setBaseUri(getvalue("baseUri"))
                    .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON)
                    .addFilter(RequestLoggingFilter.logRequestTo(stream)).
                    addFilter(ResponseLoggingFilter.logResponseTo(stream))
                    .build();
            return baseReq;
        }else
            return baseReq;




    }

    public String getvalue(String key) throws IOException {

        Properties properties =new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\91762\\IdeaProjects\\RestAPIAutomation_2024\\src\\main\\java\\properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }
}
