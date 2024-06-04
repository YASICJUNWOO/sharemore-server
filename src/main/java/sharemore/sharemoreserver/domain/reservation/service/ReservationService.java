package sharemore.sharemoreserver.domain.reservation.service;

import sharemore.sharemoreserver.domain.reservation.Reservation;
import sharemore.sharemoreserver.domain.reservation.ReservationRequest;

import java.time.LocalDateTime;

public interface ReservationService {

    Reservation addReservation(Reservation reservation);

    Reservation addReservation(ReservationRequest reservationRequest);

    Reservation findById(Long id);

    void validReservation(LocalDateTime startDateTime, LocalDateTime endDateTime, Long itemId);
}
