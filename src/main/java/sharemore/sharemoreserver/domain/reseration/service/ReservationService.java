package sharemore.sharemoreserver.domain.reseration.service;

import sharemore.sharemoreserver.domain.reseration.Reservation;

import java.time.LocalDateTime;

public interface ReservationService {

    Reservation addReservation(Reservation reservation);

    Reservation findById(Long id);

    void validReservation(LocalDateTime startDateTime, LocalDateTime endDateTime, Long itemId);
}
