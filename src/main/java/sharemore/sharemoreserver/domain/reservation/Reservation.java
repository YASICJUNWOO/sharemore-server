package sharemore.sharemoreserver.domain.reservation;

import jakarta.persistence.*;
import lombok.*;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.member.Member;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

}
