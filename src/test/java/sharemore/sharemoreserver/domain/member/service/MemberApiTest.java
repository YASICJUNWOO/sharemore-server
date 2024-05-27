package sharemore.sharemoreserver.domain.member.service;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import sharemore.sharemoreserver.ApiTest;
import sharemore.sharemoreserver.domain.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberApiTest extends ApiTest {

    @Test
    public void 회원가입_API() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword").build();

        //when
        final ExtractableResponse<Response> extract = RestAssured.given().log().all()
                .body(member)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/api/member")
                .then()
                .log().all().extract();

        //then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }


}
