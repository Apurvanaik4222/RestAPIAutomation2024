package stepDefination;

import io.cucumber.java.Before;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public static void beforeDeletePlace() throws IOException {
        StepDefinations stepDefinations =new StepDefinations();
        if(stepDefinations.place_Id ==null){
            stepDefinations.add_place_payload("APN","Marathi","Japan");
            stepDefinations.hits_using_http_method("AddPlaceAPI","Post");
            stepDefinations.verify_place_id_created_maps_to_using("APN","GetPlaceAPI");
        }

    }
}
