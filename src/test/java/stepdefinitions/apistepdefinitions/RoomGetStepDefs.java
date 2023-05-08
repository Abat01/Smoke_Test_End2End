package stepdefinitions.apistepdefinitions;

import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import pojos.Room;

import static base_urls.MedunnaBaseUrl.spec;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static stepdefinitions.MedunnaRoomStepDefs.roomNumber;

public class RoomGetStepDefs {
    @Given("user sends get request and validate")
    public void user_sends_get_request_and_validate(){
        //Set the URL

        //https://medunna.com/api/rooms?sort=createdDate,desc
        spec.pathParams("first","api","second","rooms").
                queryParam("sort","createDate,desc");
        //Set the expected data
        Room expectedData = new Room("Created By Selenium For Api Test",123,roomNumber,"SUITE",true);

        //Send the request and get the response
        Response response= given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        assertEquals(200,response.statusCode());
        JsonPath jsonPath = response.jsonPath();
        int actualRoomNumber = jsonPath.getInt("findAll{it.roomNumber==" + roomNumber + "}.roomNumber");
        String actualRoomType = jsonPath.getString("findAll{it.roomNumber==" + roomNumber + "}.roomType");
        boolean actualStatus = jsonPath.getBoolean("findAll{it.roomNumber==" + roomNumber + "}.status");
        int actualPrice = jsonPath.getInt("findAll{it.roomNumber==" + roomNumber + "}.price");
        String actualDescription = jsonPath.getString("findAll{it.roomNumber==" + roomNumber + "}.description");
        assertEquals((int)expectedData.getRoomNumber(),actualRoomNumber);
        assertEquals(expectedData.getRoomType(),actualRoomType);
        assertEquals((int)expectedData.getPrice(),actualPrice);
        assertEquals(expectedData.getDescription(),actualDescription);



    }

}
