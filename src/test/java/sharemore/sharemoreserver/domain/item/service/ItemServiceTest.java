package sharemore.sharemoreserver.domain.item.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.ItemService;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

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



}
