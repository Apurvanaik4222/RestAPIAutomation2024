package resources;

import POJO.AddPlaceN;
import POJO.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public static AddPlaceN addPlacePayload(String name,String language,String address){

        AddPlaceN addPlaceN =new AddPlaceN();
        addPlaceN.setLanguage(language);
        addPlaceN.setAddress(address);
        addPlaceN.setWebsite("https://ww.google.com");
        addPlaceN.setName(name);
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
        return addPlaceN;

    }

    public static String deletePlacePayload(String place_id){
        return"{\n" +
                "    \"place_id\":\""+place_id+"\"\n" +
                "}\n";

    }
}
