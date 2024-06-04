package sharemore.sharemoreserver.domain.reservation;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;

public class ReservationStep {

    public static ExtractableResponse<Response> 예약생성요청(ReservationRequest reservationRequest) {
        return RestAssured.given().log().all()
                .contentType("application/json")
                .body(reservationRequest)
                .when()
                .post("/api/reservation")
                .then()
                .log().all().extract();
    }
}
