package TestComponents;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import resources.Utils;

import static io.restassured.RestAssured.given;

public class Oauth2Impl {
    String code;
     String access_Token;
    @Test
    public void getCode(){

        String codeString ="https://rahulshettyacademy.com/getCourse.php?code=4%2F0AeaYSHA458FJvROIIPXcmjzPQTrcs5OPGJ21nGO8Lctmc5QXpEriKC5nND9EHs2l-pAt3w&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
         code =codeString.split("code=")[1].split("&scope")[0];
         System.out.println(code);
    }

    @Test(dependsOnMethods = "getCode")
    public void getAccessToken(){

       Response response= given().urlEncodingEnabled(false).log().all()
                .queryParams("code",code)
                .queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
                .queryParams("grant_type","authorization_code")
               .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
               .queryParams("state", "verifyfjdss")
                .when().post("https://www.googleapis.com/oauth2/v4/token")
                .then().log().all().assertThat().statusCode(200).extract().response();

       access_Token = Utils.rawToJson("access_token",response);

    }

    @Test(dependsOnMethods = "getAccessToken")
    public void actualRequest(){
        given().log().all().queryParam("access_token",access_Token)
                .when().get("https://rahulshettyacademy.com/getCourse.php").then().log().all().assertThat().statusCode(200);

    }
}
