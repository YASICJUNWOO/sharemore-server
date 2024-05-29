package sharemore.sharemoreserver.domain.item;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;
import sharemore.sharemoreserver.domain.member.Member;

public class ItemStep {

    public static ExtractableResponse<Response> 아이템등록요청(final ItemRequest member){

        return RestAssured.given().log().all()
                .body(member)
                .contentType("application/json")
                .when()
                .post("/api/item/add")
                .then()
                .log().all().extract();
    }

}
