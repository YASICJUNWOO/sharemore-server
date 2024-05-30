package sharemore.sharemoreserver.domain.item;

import jakarta.persistence.*;
import lombok.*;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;
import sharemore.sharemoreserver.domain.member.Member;

@Entity
@Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String category;

    private int price;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Member owner;

    public void update(Item updatedItem) {
        this.title = updatedItem.getTitle();
        this.description = updatedItem.getDescription();
        this.category = updatedItem.getCategory();
        this.price = updatedItem.getPrice();
    }
}