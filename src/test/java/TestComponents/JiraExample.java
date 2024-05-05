package TestComponents;

import Files.Payload;
import Files.ReusableCode;
import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class JiraExample {


    @Test
    public void jiraEndtoEnd(){

        RestAssured.baseURI ="http://localhost:8080/";

        //Cookie Based Authentication
        SessionFilter sessionFilter =new SessionFilter();
        given().log().all().body("{ \"username\": \"myuser\", \"password\": \"mypassword\" }").header("Content-Type","applicatiion/json")
                .filter(sessionFilter)
                .when().post("/rest/auth/1/session")
                .then().assertThat().statusCode(200).extract().response().asString();

        //Creating an issue


        String createIssueResponse = given().log().all().body(Payload.createIssuePayload())
                .filter(sessionFilter)
                .when().post(" /rest/api/2/issue")
                .then().assertThat().statusCode(200).extract().response().asString();

        String Id =ReusableCode.rawtoJson(createIssueResponse,"id");


        //Updating an comment

        given().log().all().body(Payload.createIssuePayload())
                .pathParam("key",Id)
                .filter(sessionFilter)
                .when().post(" /rest/api/2/issue/{key}/comment/1234")
                .then().assertThat().statusCode(200).extract().response().asString();


        //Adding an attachment

        given().log().all()
                .header("content-Type","multipart/form-data")
                .header("X-Atlassian-Token","no-check")
                .pathParam("key",Id)
                .multiPart("file",new File("restAPI.txt"))
                .pathParam("key",Id)
                .filter(sessionFilter)
                .when().post(" /rest/api/2/issue/{key}/comment/1234")
                .then().assertThat().statusCode(200).extract().response().asString();









    }
}
