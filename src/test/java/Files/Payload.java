package Files;

public class Payload {

    public static String addPlacePayload(){

        return "{\n" +
        "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}\n";
    }

    public  static  String updatePlacePayload(String place_id,String newAddress){
        return "{\n" +
                "\"place_id\":\""+place_id+"\",\n" +
                "\"address\":\""+newAddress+"\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}\n";
    }


    public static  String complexJsonParse(){

        return "{\r\n " +
        "  \"dashboard\": {\r\n" +
                "    \"purchaseAmount\": 1162,\r\n" +
                "    \"website\": \"rahulshettyacademy.com\"\r\n" +
                "  },\r\n" +
                "  \"courses\": [\r\n" +
                "    {\r\n" +
                "      \"title\": \"Selenium Python\",\r\n" +
                "      \"price\": 50,\r\n" +
                "      \"copies\": 6\r\n" +
                "    },\r\n" +
                "    {\r\n" +
                "      \"title\": \"Cypress\",\r\n" +
                "      \"price\": 40,\r\n" +
                "      \"copies\": 4\r\n" +
                "    },\r\n" +
                "    {\r\n" +
                "      \"title\": \"RPA\",\r\n" +
                "      \"price\": 45,\r\n" +
                "      \"copies\": 10\r\n" +
                "    },\r\n" +
                "     {\r\n" +
                "      \"title\": \"Appium\",\r\n" +
                "      \"price\": 36,\r\n" +
                "      \"copies\": 7\r\n" +
                "    }\r\n" +
                "    \r\n" +
                "    \r\n" +
                "    \r\n" +
                "  ]\r\n" +
                "}\r\n" +
                "";


    }

    public  static String addBookPayload(String isbn ,String aisle){

       return "{\n" +
               "\n" +
               "\"name\":\"Learn Appium Automation with Java\",\n" +
               "\"isbn\":\""+isbn+"\",\n" +
               "\"aisle\":\""+aisle+"\",\n" +
               "\"author\":\"John foe\"\n" +
               "}\n";

    }


    public  static String deleteBookPayload(String id){

        return "{\n" +
                " \n" +
                "\"ID\" : \""+id+"\"\n" +
                " \n" +
                "} \n";

    }


    public  static String createIssuePayload(){

        return "{\n" +
                "    \"update\": {\n" +
                "        \"worklog\": [\n" +
                "            {\n" +
                "                \"add\": {\n" +
                "                    \"timeSpent\": \"60m\",\n" +
                "                    \"started\": \"2011-07-05T11:05:00.000+0000\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"fields\": {\n" +
                "        \"project\": {\n" +
                "            \"id\": \"10000\"\n" +
                "        },\n" +
                "        \"summary\": \"something's wrong\",\n" +
                "        \"issuetype\": {\n" +
                "            \"id\": \"10000\"\n" +
                "        },\n" +
                "        \"assignee\": {\n" +
                "            \"name\": \"homer\"\n" +
                "        },\n" +
                "        \"reporter\": {\n" +
                "            \"name\": \"smithers\"\n" +
                "        },\n" +
                "        \"priority\": {\n" +
                "            \"id\": \"20000\"\n" +
                "        },\n" +
                "        \"labels\": [\n" +
                "            \"bugfix\",\n" +
                "            \"blitz_test\"\n" +
                "        ],\n" +
                "        \"timetracking\": {\n" +
                "            \"originalEstimate\": \"10\",\n" +
                "            \"remainingEstimate\": \"5\"\n" +
                "        },\n" +
                "        \"security\": {\n" +
                "            \"id\": \"10000\"\n" +
                "        },\n" +
                "        \"versions\": [\n" +
                "            {\n" +
                "                \"id\": \"10000\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"environment\": \"environment\",\n" +
                "        \"description\": \"description\",\n" +
                "        \"duedate\": \"2011-03-11\",\n" +
                "        \"fixVersions\": [\n" +
                "            {\n" +
                "                \"id\": \"10001\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"components\": [\n" +
                "            {\n" +
                "                \"id\": \"10000\"\n" +
                "            }\n" +
                "        ],\n" +
                "        \"customfield_30000\": [\n" +
                "            \"10000\",\n" +
                "            \"10002\"\n" +
                "        ],\n" +
                "        \"customfield_80000\": {\n" +
                "            \"value\": \"red\"\n" +
                "        },\n" +
                "        \"customfield_20000\": \"06/Jul/11 3:25 PM\",\n" +
                "        \"customfield_40000\": \"this is a text field\",\n" +
                "        \"customfield_70000\": [\n" +
                "            \"jira-administrators\",\n" +
                "            \"jira-software-users\"\n" +
                "        ],\n" +
                "        \"customfield_60000\": \"jira-software-users\",\n" +
                "        \"customfield_50000\": \"this is a text area. big text.\",\n" +
                "        \"customfield_10000\": \"09/Jun/81\"\n" +
                "    }\n" +
                "}";

    }
}
