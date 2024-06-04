package sharemore.sharemoreserver.domain.item;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;
import sharemore.sharemoreserver.domain.member.Member;

public class ItemStep {

    public static ExtractableResponse<Response> 아이템등록요청(ItemRequest itemRequest){

        return RestAssured.given().log().all()
                .body(itemRequest)
                .contentType("application/json")
                .when()
                .post("/api/item/add")
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 아이템조회요청(Long id) {
        return RestAssured.given().log().all()
                .when()
                .get("/api/item/" + id)
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 아이템수정요청(Long id, ItemRequest updateRequest) {
        return RestAssured.given().log().all()
                .body(updateRequest)
                .contentType("application/json")
                .when()
                .patch("/api/item/" + id)
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 아이템삭제요청(Long id) {
        return RestAssured.given().log().all()
                .when()
                .delete("/api/item/" + id)
                .then()
                .log().all().extract();
    }
}
