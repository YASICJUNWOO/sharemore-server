package sharemore.sharemoreserver.domain.member.service;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import sharemore.sharemoreserver.ApiTest;
import sharemore.sharemoreserver.domain.member.Member;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberApiTest extends ApiTest {

    @Test
    public void 회원가입_API() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .name("testName")
                .phone("010-1234-5678")
                .build();

        //when
        final ExtractableResponse<Response> extract = MemberStep.회원등록요청(member);


        //then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.CREATED.value());

    }

    @Test
    public void 로그인_API() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .name("testName")
                .phone("010-1234-5678")
                .build();

        MemberStep.회원등록요청(member);

        //when
        final ExtractableResponse<Response> extract = MemberStep.로그인요청(member);

        //then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 로그인_실패_API() {

        //given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .name("testName")
                .phone("010-1234-5678")
                .build();

        Member testMember = Member.builder()
                .email("testEmail")
                .password("testPassword2")
                .name("testName")
                .phone("010-1234-5678")
                .build();

        MemberStep.회원등록요청(member);

        //when
        final ExtractableResponse<Response> extract = MemberStep.로그인요청(testMember);

        //then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }


}
