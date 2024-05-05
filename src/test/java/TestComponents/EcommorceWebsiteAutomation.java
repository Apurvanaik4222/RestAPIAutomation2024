package TestComponents;

import Files.ReusableCode;
import POJO.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EcommorceWebsiteAutomation {
    static String acces_Token;
    static String productId;
    static String productOrderId;

    @Test
    public void loginTest(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("apurvanaik42@academy.com");
        loginRequest.setUserPassword("Improve@1234");

        RequestSpecification baseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();

        RequestSpecification req =given().log().all().spec(baseReq).body(loginRequest);


       LoginResponse loginResponse = req.when().post("/api/ecom/auth/login").then().assertThat().statusCode(200).extract().response().as(LoginResponse.class);

        acces_Token =loginResponse.getToken();
    }


    @Test(dependsOnMethods = "loginTest")
    public  void addProduct(){
        RequestSpecification addProductbaseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.MULTIPART).addHeader("Authorization",acces_Token).build();

       String addProductResponse = given().log().all().relaxedHTTPSValidation().urlEncodingEnabled(false).spec(addProductbaseReq)
                .param("productName","Apple")
                .param("productCategory","fashion")
                .param("productSubCategory","shirts")
                .param("productPrice","1234")
                .param("productDescription","Addias Originals")
                .param("productFor","Men")
                .multiPart("productImage",new File("C:\\Users\\91762\\OneDrive\\Desktop\\AppleImg.jpg"))
                .when().post("/api/ecom/product/add-product")
                .then().log().all().assertThat().statusCode(201).body("message",equalTo("Product Added Successfully")).extract().response().asString();

        productId = ReusableCode.rawtoJson(addProductResponse,"productId");
        System.out.println(productId);

    }


    @Test(dependsOnMethods = "addProduct")
    public void placeOrder(){

        PlaceOrderRequest placeOrderRequest =new PlaceOrderRequest();
        OrderDetails orders =new OrderDetails();
        orders.setCountry("British Indian Ocean Territory");
        orders.setProductOrderedId(productId);

        List<OrderDetails> orderList =new ArrayList<>();
        orderList.add(orders);

        placeOrderRequest.setOrders(orderList);

        RequestSpecification placeOrderbaseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization",acces_Token).build();

        RequestSpecification placeOrderReq = given().log().all().spec(placeOrderbaseReq).body(placeOrderRequest);

        PlaceOrderResponse placeOrderResponse =  placeOrderReq.when().post("/api/ecom/order/create-order").then().log().all().assertThat().statusCode(201).extract().as(PlaceOrderResponse.class);


        productOrderId = placeOrderResponse.getProductOrderId().get(0);
        System.out.println(productId);

    }

    @Test(dependsOnMethods = "placeOrder")
    public void deleteProduct(){

        RequestSpecification deleteProductbaseReq =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization",acces_Token).addPathParam("productOrderId",productOrderId).build();


        RequestSpecification ProductbaseReq  = given().log().all().spec(deleteProductbaseReq);
        ProductbaseReq.when().delete("/api/ecom/product/delete-product/{productOrderId}")
                .then().assertThat().statusCode(200).body("msg",equalTo("Product Deleted Successfully"));




    }


}
