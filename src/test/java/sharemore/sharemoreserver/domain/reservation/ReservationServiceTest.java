package sharemore.sharemoreserver.domain.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.service.ItemService;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberService;
import sharemore.sharemoreserver.domain.reseration.Reservation;
import sharemore.sharemoreserver.domain.reseration.service.ReservationService;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ReservationServiceTest {

    @Autowired
    ReservationService reservationService;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;

    @Test
    public void 예약_생성() {

        // given
        Member member = Member.builder()
                .email("testEmail")
                .password("testPassword")
                .name("testName")
                .phone("010-1234-5678")
                .build();

        Item item = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .owner(member)
                .build();

        memberService.join(member);
        itemService.addItem(item);

        Reservation reservation = Reservation.builder()
                .member(new Member())
                .item(new Item())
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now().plusDays(1))
                .build();

        // when
        Reservation savedReservation = reservationService.addReservation(reservation);

        // then\
        assertThat(savedReservation).isEqualTo(reservation);

    }

}
