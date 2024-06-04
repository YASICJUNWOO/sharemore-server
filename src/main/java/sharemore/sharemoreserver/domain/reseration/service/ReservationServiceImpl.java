package sharemore.sharemoreserver.domain.reseration.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharemore.sharemoreserver.domain.reseration.Reservation;
import sharemore.sharemoreserver.domain.reseration.repository.ReservationRepository;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
