package sharemore.sharemoreserver.domain.reservation.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.service.ItemService;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberService;
import sharemore.sharemoreserver.domain.reservation.Reservation;
import sharemore.sharemoreserver.domain.reservation.ReservationRequest;
import sharemore.sharemoreserver.domain.reservation.repository.ReservationRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberService memberService;
    private final ItemService itemService;

    @Override
    public Reservation addReservation(Reservation reservation) {
        validReservation(reservation.getStartDateTime(), reservation.getEndDateTime(), reservation.getItem().getId());
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation addReservation(ReservationRequest reservationRequest) {
        Item item = itemService.findItemById(reservationRequest.getItemId());
        Member member = memberService.findByEmail(reservationRequest.getMemberEmail());

        Reservation reservation = reservationRequest.toEntity(reservationRequest, item, member);
        return addReservation(reservation);
    }

    @Override
    public Reservation findById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약이 존재하지 않습니다."));
    }

    @Override
    public void validReservation(LocalDateTime startDateTime, LocalDateTime endDateTime, Long itemId) {

        if(reservationRepository.countByItemIdAndDateTime(itemId, startDateTime, endDateTime) > 0){
            throw new IllegalStateException("이미 예약된 시간입니다.");
        }

    }
}
