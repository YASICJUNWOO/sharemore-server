package sharemore.sharemoreserver.domain.item;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.NonNull;
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

    @Builder.Default
    @NonNull
    private Boolean isDeleted = false;

    public void update(Item updatedItem) {
        this.title = updatedItem.getTitle();
        this.description = updatedItem.getDescription();
        this.category = updatedItem.getCategory();
        this.price = updatedItem.getPrice();
    }

    public void delete() {
        this.isDeleted = true;
    }

}