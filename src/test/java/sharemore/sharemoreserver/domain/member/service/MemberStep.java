package sharemore.sharemoreserver.domain.member.service;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import sharemore.sharemoreserver.domain.member.Member;

public class MemberStep {

    public static ExtractableResponse<Response> 회원등록요청(final Member member){

        return RestAssured.given().log().all()
                .body(member)
                .contentType("application/json")
                .when()
                .post("/api/member/sign-up")
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 로그인요청(final Member member){

        return RestAssured.given().log().all()
                .body(member)
                .contentType("application/json")
                .when()
                .post("/api/member/login")
                .then()
                .log().all().extract();
    }

    public static ExtractableResponse<Response> 모든회원조회요청(){

        return RestAssured.given().log().all()
                .when()
                .get("/api/admin/member/all")
                .then()
                .log().all().extract();

    }

}
