package sharemore.sharemoreserver.domain.reservation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.member.Member;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequest {

    private String memberEmail;
    private Long itemId;
    private String startDateTime;
    private String endDateTime;

    public Reservation toEntity(ReservationRequest reservationRequest, Item item, Member member) {
        return Reservation.builder()
                .member(member)
                .item(item)
                .startDateTime(LocalDateTime.parse(reservationRequest.startDateTime))
                .endDateTime((LocalDateTime.parse(reservationRequest.endDateTime)))
                .build();
    }

}
