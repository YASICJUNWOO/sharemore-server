package sharemore.sharemoreserver.domain.item.dto;

import lombok.Builder;
import lombok.Getter;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.member.Member;

@Getter
@Builder
public class ItemRequest {

    private String title;
    private String description;
    private String category;
    private int price;

    private String ownerEmail;

    public Item toEntity(ItemRequest request, Member owner) {
        return Item.builder()
                .title(title)
                .description(description)
                .category(category)
                .price(price)
                .owner(owner)
                .build();
    }

}
