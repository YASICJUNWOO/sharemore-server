package sharemore.sharemoreserver.domain.reseration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharemore.sharemoreserver.domain.reseration.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
