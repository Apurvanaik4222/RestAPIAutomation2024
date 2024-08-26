package TestComponents;

import Files.Payload;
import Files.ReusableCode;
import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class BooksTest {

    @Test(dataProvider = "addBookData")
    public void addBook(String isbn ,String aisle) throws IOException {

        RestAssured.baseURI ="http://216.10.245.166";

        String addplaceResponse =given().log().all().header("Content-Type","application/json")
               //Reading Data From json File
                //.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\91762\\IdeaProjects\\RestAPIAutomation_2024\\src\\test\\java\\Files\\addPlace.json"))))
                .body(Payload.addBookPayload(isbn,aisle))
                .when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).body("Msg",equalTo("successfully added")).header("Server","Apache").extract().response().asString();

        String id =ReusableCode.rawtoJson(addplaceResponse,"ID");
        System.out.println(id);
    }


    @Test(dependsOnMethods = "addBook",dataProvider = "deleteBookData")
    public void deletePlace(String id){
        RestAssured.baseURI ="http://216.10.245.166";
        given().log().all().body(Payload.deleteBookPayload(id))
                .when().post("/Library/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("book is successfully deleted")).header("Server","Apache").extract().response().asString();


    }



    @DataProvider(name="addBookData")
    public Object[][] addBookData(){
        Object[][] data =new Object[][]{{"baapm","1123"},{"baapm","1124"},{"baapm","1125"}};
        return data;
    }


    @DataProvider(name="deleteBookData")
    public Object[][] deleteBookData(){
        Object[][] data =new Object[][]{{"baapm1123"},{"baapm1124"},{"baapm1125"}};
        return data;
    }
}
