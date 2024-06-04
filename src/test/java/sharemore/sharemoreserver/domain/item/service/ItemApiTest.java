package sharemore.sharemoreserver.domain.item.service;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import sharemore.sharemoreserver.ApiTest;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.ItemStep;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberStep;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemApiTest extends ApiTest {

    @Test
    public void 아이템_등록_API() {

        // given
        Member owner = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build();

        MemberStep.회원등록요청(owner);


        ItemRequest request = ItemRequest.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .ownerEmail(owner.getEmail())
                .build();


        // when
        ExtractableResponse<Response> extract = ItemStep.아이템등록요청(request);

        // then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void 아이템_조회_API() {

        // given
        Member owner = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build();

        MemberStep.회원등록요청(owner);

        ItemRequest request = ItemRequest.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .ownerEmail(owner.getEmail())
                .build();

        ExtractableResponse<Response> response = ItemStep.아이템등록요청(request);
        Item item = response.as(Item.class);

        // when
        ExtractableResponse<Response> extract2 = ItemStep.아이템조회요청(item.getId());

        // then
        assertThat(extract2.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 아이템_수정_API() {

        // given
        Member owner = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build();

        MemberStep.회원등록요청(owner);

        ItemRequest request = ItemRequest.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .ownerEmail(owner.getEmail())
                .build();

        ExtractableResponse<Response> response = ItemStep.아이템등록요청(request);
        Item item = response.as(Item.class);

        ItemRequest updateRequest = ItemRequest.builder()
                .title("아이템2")
                .description("아이템2 설명")
                .category("가전")
                .price(20000)
                .ownerEmail(owner.getEmail())
                .build();

        // when
        ExtractableResponse<Response> extract = ItemStep.아이템수정요청(item.getId(), updateRequest);

        // then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void 아이템_삭제_API() {

        // given
        Member owner = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build();

        MemberStep.회원등록요청(owner);

        ItemRequest request = ItemRequest.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .ownerEmail(owner.getEmail())
                .build();

        ExtractableResponse<Response> response = ItemStep.아이템등록요청(request);
        Item item = response.as(Item.class);

        // when
        ExtractableResponse<Response> extract = ItemStep.아이템삭제요청(item.getId());

        // then
        assertThat(extract.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
