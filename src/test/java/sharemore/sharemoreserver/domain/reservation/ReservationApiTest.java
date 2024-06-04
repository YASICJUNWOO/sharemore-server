package sharemore.sharemoreserver.domain.reservation;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sharemore.sharemoreserver.ApiTest;
import sharemore.sharemoreserver.domain.item.ItemStep;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberStep;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Transactional
public class ReservationApiTest extends ApiTest {

    @Test
    @Rollback
    public void 예약_등록_API() {

        // given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .name("testName")
                .phone("010-1234-5678")
                .build();

        MemberStep.회원등록요청(member);

        ItemRequest item = ItemRequest.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .ownerEmail("testEmail")
                .build();

        ItemStep.아이템등록요청(item);

        ReservationRequest reservationRequest = ReservationRequest.builder()
                .memberEmail("testEmail")
                .itemId(1L)
                .startDateTime(LocalDateTime.parse("2024-01-15T10:00:00"))
                .endDateTime(LocalDateTime.parse("2024-01-15T10:30:00"))
                .build();

        // when
        ExtractableResponse<Response> response = ReservationStep.예약생성요청(reservationRequest);

        // then
        assertThat(response.statusCode()).isEqualTo(201);

    }

}
