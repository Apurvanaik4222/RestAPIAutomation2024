package TestComponents;

import Files.ReusableCode;
import POJO.GetCourses;
import POJO.WebAutomation;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ClientCredentialsEg {
    static String access_token;

    @Test
    public void generateOAuthToken(){

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String othTokenResponse =given().log().all().formParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type","client_credentials")
                .formParams("scope","trust")
                .when().post("/oauthapi/oauth2/resourceOwner/token")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        access_token =ReusableCode.rawtoJson(othTokenResponse,"access_token");


    }


    @Test(dependsOnMethods = "generateOAuthToken")
    public void actaulRequest(){

        RestAssured.baseURI="https://rahulshettyacademy.com";

       GetCourses getCourses = given().log().all().queryParam("access_token",access_token).when().get("/oauthapi/getCourseDetails")
                .then().log().all().assertThat().statusCode(401).extract().as(GetCourses.class);


       System.out.println(getCourses.getInstructor());
        System.out.println(getCourses.getExpertise());
        System.out.println(getCourses.geturl());
        System.out.println(getCourses.getLinkedIn());
        //System.out.println(getCourses.getInstructor());

        //Print price and title of SoapUI Webservices testing under Api

        System.out.println(getCourses.getCourses().getApi().get(1).getCourseTitle());
        System.out.println(getCourses.getCourses().getApi().get(1).getPrice());


        //print all courses title under WebAutomation

        String [] TitleList ={"Selenium Webdriver Java","Cypress","Protractor"};

       List<String> actualList =new ArrayList<>();

        List< WebAutomation>  webAutomationList =getCourses.getCourses().getWebAutomation();

        for (int i=0;i<webAutomationList.size();i++){
            actualList.add(getCourses.getCourses().getWebAutomation().get(i).getCourseTitle());
        }


        //Converting Array to ArrayList

     List<String> expectedTitleList =Arrays.asList(TitleList);

        Assert.assertEquals(expectedTitleList,actualList);




        //




    }
}
