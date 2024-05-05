package TestComponents;

import Files.Payload;
import Files.ReusableCode;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BasicsTest {

    public static String place_Id;
    public static String newAddress;

   @Test
    public void addPlace(){

        RestAssured.baseURI ="https://rahulshettyacademy.com";

        String addPlaceResponse =given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body(Payload.addPlacePayload())
                .when().post("/maps/api/place/add/json?key=qaclick123")
                .then().log().all().assertThat().statusCode(200)
                .body("status",equalTo("OK")).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

       place_Id = ReusableCode.rawtoJson(addPlaceResponse,"place_id");
    }


    @Test(dependsOnMethods = "addPlace")
    public void updatePlace(){
newAddress="70 winter walk, USA";
        RestAssured.baseURI ="https://rahulshettyacademy.com";

        given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_Id)
                .body(Payload.updatePlacePayload(place_Id,newAddress))
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

    }

    @Test(dependsOnMethods = "updatePlace")
    public  void getPlace(){
        RestAssured.baseURI ="https://rahulshettyacademy.com";

        String getPlacePayload =given().log().all().queryParam("key","qaclick123").queryParam("place_id",place_Id)
                .when().get("/maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        String actualAddress =ReusableCode.rawtoJson(getPlacePayload,"address");

        Assert.assertEquals(newAddress,actualAddress);

    }
}
