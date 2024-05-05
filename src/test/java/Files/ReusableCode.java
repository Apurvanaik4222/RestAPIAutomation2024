package Files;

import io.restassured.path.json.JsonPath;

public class ReusableCode {

    public static String rawtoJson(String response,String key){
        JsonPath jsonPath =new JsonPath(response);
        return jsonPath.get(key);
    }
}
