Feature:Validating Add Place API

  @AddPlace
  Scenario Outline: Verify place gets succesfully added using AddPlace API new
    Given AddPlace payload "<name>" "<language>" "<address>"
    When hits "AddPlaceAPI" using http "post" method
    Then gets sucessfull response with status code as"200"
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify placeID created maps to "<name>" using "GetPlaceAPI"

   Examples:
    |name |language|address |
    |Apurva|English|Pune    |


    @DeletePlace
    Scenario: Verify Delete Place API functionality is working successfully
    Given DeletePlaceAPI payload
    When hits "DeletePlaceAPI" using http "post" method
    Then gets sucessfull response with status code as"200"


















