package TestComponents;

import Files.Payload;
import Files.ReusableCode;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ComplexJsonParse {

    @Test
    public void test(){

        //Print Purchase Amount

        JsonPath js =new JsonPath(Payload.complexJsonParse());
        int purchaseAmt =js.getInt("dashboard.purchaseAmount");
        System.out.println(purchaseAmt);
        System.out.println("------------------------------------");
//Print Title of the first course
        String title =js.get("courses[0].title");
        System.out.println(title);
        System.out.println("------------------------------------");
        //Print no of copies sold by RPA Course
       int coursesSize = js.getInt("courses.size()");
        System.out.println(coursesSize);
        System.out.println("------------------------------------");

        for(int i=0;i<coursesSize;i++){
          String courseTitle =  js.get("courses["+i+"].title").toString();
          if(courseTitle.equalsIgnoreCase("Appium")){
              int copies =js.getInt("courses["+i+"].copies");
              System.out.println(copies);
              break;
          }
        }

        System.out.println("------------------------------------");

        //Print All course titles and their respective Prices
        for(int i=0;i<coursesSize;i++){
         String title1 =js.get("courses["+i+"].title").toString();
            String price1 =js.get("courses["+i+"].price").toString();


            System.out.println(title1);
            System.out.println(price1);
            }
        System.out.println("------------------------------------");



        //Purchase Amount equals to all copies sold
        int sum =0;
        for(int i=0;i<coursesSize;i++){
            int price1 =js.getInt("courses["+i+"].price");
            int copies1 =js.getInt("courses["+i+"].copies");
            int amount =price1 *copies1;
            sum =sum+amount;

        }

        System.out.println(sum);

        Assert.assertEquals(sum,purchaseAmt);

        System.out.println("------------------------------------");

    }
}
