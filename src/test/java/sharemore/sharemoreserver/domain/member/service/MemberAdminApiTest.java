package sharemore.sharemoreserver.domain.member.service;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import sharemore.sharemoreserver.ApiTest;
import sharemore.sharemoreserver.domain.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberAdminApiTest extends ApiTest {

    @Test
    public void 모든_회원_조회_API() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .name("testName")
                .phone("010-1234-5678")
                .build();

        //when
        MemberStep.회원등록요청(member);
        final ExtractableResponse<Response> extract = MemberStep.모든회원조회요청();

        //then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.OK.value());

    }

}
