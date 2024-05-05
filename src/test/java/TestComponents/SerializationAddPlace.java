package TestComponents;

import POJO.AddPlaceN;
import POJO.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SerializationAddPlace {

    @Test
    public void addPlace(){


        AddPlaceN addPlaceN =new AddPlaceN();
        addPlaceN.setLanguage("English");
        addPlaceN.setAddress("83 yerwada");
        addPlaceN.setWebsite("https://ww.google.com");
        addPlaceN.setName("Apurva");
        addPlaceN.setPhone_number("7620172242");
        addPlaceN.setAccuracy(56);

        Location location =new Location();
        location.setLat(-38.383494);
        location.setLng(66.383494);
        addPlaceN.setLocation(location);

        List<String> list =new ArrayList<>();
        list.add("shoe park");
        list.add("shop");

        addPlaceN.setTypes(list);


        RequestSpecification baseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
        ResponseSpecification resp =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        RequestSpecification req =  given().log().all().spec(baseReq).body(addPlaceN);

                    req.when().post("/maps/api/place/add/json")
                .then().log().all().spec(resp).body("status",equalTo("OK")).header("Server","Apache/2.4.52 (Ubuntu)");
    }
}
