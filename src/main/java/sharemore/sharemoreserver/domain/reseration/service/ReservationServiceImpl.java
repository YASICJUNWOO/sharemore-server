package sharemore.sharemoreserver.domain.reseration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharemore.sharemoreserver.domain.reseration.Reservation;
import sharemore.sharemoreserver.domain.reseration.repository.ReservationRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        validReservation(reservation.getStartDateTime(), reservation.getEndDateTime(), reservation.getItem().getId());
        return reservationRepository.save(reservation);
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
