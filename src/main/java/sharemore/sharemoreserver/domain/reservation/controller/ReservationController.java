package sharemore.sharemoreserver.domain.reservation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharemore.sharemoreserver.domain.reservation.Reservation;
import sharemore.sharemoreserver.domain.reservation.ReservationRequest;
import sharemore.sharemoreserver.domain.reservation.service.ReservationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody ReservationRequest reservationRequest){
        Reservation reservation = reservationService.addReservation(reservationRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservation);
    }
}
