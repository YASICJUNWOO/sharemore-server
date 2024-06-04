package sharemore.sharemoreserver.domain.reservation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.service.ItemService;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberService;
import sharemore.sharemoreserver.domain.reservation.service.ReservationService;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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

    @Test
    public void 예약_ID로_조회() {

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

        Reservation savedReservation = reservationService.addReservation(reservation);

        // when
        Reservation foundReservation = reservationService.findById(savedReservation.getId());

        // then
        assertThat(foundReservation).isEqualTo(savedReservation);

    }

    @Test
    @DisplayName("중복 예약 방지 - 기존 예약의 시작 시간이 끝 시간일 때")
    public void 중복_예약_방지1() {

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

        makeBasicReservation(member, item);

        // when
        Reservation duplicateReservation = Reservation.builder()
                .member(member)
                .item(item)
                .startDateTime(LocalDateTime.parse("2024-02-15T09:00:00"))
                .endDateTime(LocalDateTime.parse("2024-02-15T10:00:00"))
                .build();

        // then
        assertThatThrownBy(() -> reservationService.addReservation(duplicateReservation))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 예약된 시간입니다.");

    }

    @Test
    @DisplayName("중복 예약 방지 - 기존 예약의 끝 시간이 시작 시간일 때")
    public void 중복_예약_방지2() {

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

        makeBasicReservation(member, item);

        // when
        Reservation duplicateReservation = Reservation.builder()
                .member(member)
                .item(item)
                .startDateTime(LocalDateTime.parse("2024-02-15T11:00:00"))
                .endDateTime(LocalDateTime.parse("2024-02-15T12:00:00"))
                .build();

        // then
        assertThatThrownBy(() -> reservationService.addReservation(duplicateReservation))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 예약된 시간입니다.");

    }

    @Test
    @DisplayName("중복 예약 방지 - 기존 예약에 겹치는 시간으로 예약할 때")
    public void 중복_예약_방지3() {

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

        makeBasicReservation(member, item);

        // when
        Reservation duplicateReservation = Reservation.builder()
                .member(member)
                .item(item)
                .startDateTime(LocalDateTime.parse("2024-02-15T09:00:00"))
                .endDateTime(LocalDateTime.parse("2024-02-15T10:30:00"))
                .build();

        // then
        assertThatThrownBy(() -> reservationService.addReservation(duplicateReservation))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("이미 예약된 시간입니다.");

    }

    private void makeBasicReservation(Member member, Item item) {

        memberService.join(member);
        itemService.addItem(item);

        Reservation reservation = Reservation.builder()
                .member(member)
                .item(item)
                .startDateTime(LocalDateTime.parse("2024-02-15T10:00:00"))
                .endDateTime(LocalDateTime.parse("2024-02-15T11:00:00"))
                .build();

        reservationService.addReservation(reservation);
    }

}
