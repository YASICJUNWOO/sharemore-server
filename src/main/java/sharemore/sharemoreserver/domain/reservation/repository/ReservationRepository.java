package sharemore.sharemoreserver.domain.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sharemore.sharemoreserver.domain.reservation.Reservation;

import java.time.LocalDateTime;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.item.id = :itemId AND r.startDateTime <= :endDateTime AND r.endDateTime >= :startDateTime")
    long countByItemIdAndDateTime(@Param("itemId") Long itemId, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

}
