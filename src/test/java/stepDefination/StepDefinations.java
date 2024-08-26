package stepDefination;
//Apurva
import Files.Payload;
import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinations extends Utils{

    RequestSpecification req;
    ResponseSpecification resp;
    static Response response;
    static String place_Id;

    @Given("AddPlace payload {string} {string} {string}")
    public void add_place_payload(String name, String language, String address) throws IOException {



         resp =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        req =  given().log().all().spec(requestSpecification()).body(TestDataBuild.addPlacePayload(name,language,address));


    }

    @When("hits {string} using http {string} method")
    public void hits_using_http_method(String resource, String method) {
        APIResources apiResources =APIResources.valueOf(resource);
        if(method.equalsIgnoreCase("post")) {
            response = req.when().post(apiResources.getResource());
        } else if (method.equalsIgnoreCase("get")) {
            response = req.when().get(apiResources.getResource());


        }
        else if (method.equalsIgnoreCase("delete")) {
            response = req.when().delete(apiResources.getResource());


        }

    }

    @Then("gets sucessfull response with status code as\"{int}\"")
    public void gets_sucessfull_response_with_status_code_as(Integer int1) {
      //req.then().body("status",equalTo("OK")).header("Server","Apache/2.4.52 (Ubuntu)");
        response.then().log().all().spec(resp);
        //.header("Server","Apache/2.4.52 (Ubuntu)");

    }

    @Then("{string} in response body is {string}")
    public void in_response_body_is(String key, String expectyedValue) {
        String actualVal =Utils.rawToJson(key,response);
        Assert.assertEquals(actualVal,expectyedValue);

    }


    @Then("verify placeID created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
         place_Id =Utils.rawToJson("place_id",response);
        req =given().spec(requestSpecification()).queryParam("place_id",place_Id);
       hits_using_http_method(resource,"get");
       String actualName =Utils.rawToJson("name",response);

       Assert.assertEquals(actualName,expectedName);
       System.out.println("EOD");




    }


    @Given("DeletePlaceAPI payload")
    public void delete_place_api_payload() throws IOException {
        req =given().log().all().spec(requestSpecification()).body(Payload.deleteBookPayload(place_Id));
    }


}
