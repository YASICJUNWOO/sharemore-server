package sharemore.sharemoreserver.domain.reseration.service;

import sharemore.sharemoreserver.domain.reseration.Reservation;

public interface ReservationService {

    Reservation addReservation(Reservation reservation);

    Reservation findById(Long id);
}
