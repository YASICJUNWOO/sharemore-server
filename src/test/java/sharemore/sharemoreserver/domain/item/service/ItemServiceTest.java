package sharemore.sharemoreserver.domain.item.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.ItemService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemServiceTest {

    @Autowired ItemService itemService;

    @Test
    void 아이템_추가() {

        // given
        Item item = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .build();

        // when
        Item savedItem = itemService.addItem(item);

        // then
        assertThat(savedItem).isEqualTo(item);

    }

    @Test
    public void 아이템_ID로_조회() {
        // given
        Item item = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .build();

        Item savedItem = itemService.addItem(item);

        // when
        Item foundItem = itemService.findItemById(savedItem.getId());

        // then
        assertThat(foundItem).isEqualTo(savedItem);
    }



}
