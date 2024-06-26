package sharemore.sharemoreserver.domain.item.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback
    void 아이템_추가() {

        // given
        Member owner = memberService.join(Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build());

        Item item = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .owner(owner)
                .build();

        // when
        Item savedItem = itemService.addItem(item);

        // then
        assertThat(savedItem).isEqualTo(item);

    }

    @Test
    @Rollback
    public void 아이템_ID로_조회() {

        // given
        Member owner = memberService.join(Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build());

        Item item = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .owner(owner)
                .build();

        Item savedItem = itemService.addItem(item);

        // when
        Item foundItem = itemService.findItemById(savedItem.getId());

        // then
        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    @Rollback
    public void 아이템_수정() {

        // given
        Member owner = memberService.join(Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build());

        Item item = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .owner(owner)
                .build();

        Item savedItem = itemService.addItem(item);

        // when
        Item updatedItem = Item.builder()
                .id(savedItem.getId())
                .title("아이템2")
                .description("아이템2 설명")
                .category("가전제품")
                .price(20000)
                .owner(owner)
                .build();

        Item modifiedItem = itemService.updateItem(savedItem.getId(), updatedItem);

        // then
        assertThat(modifiedItem).isEqualTo(updatedItem);
    }

    @Test
    @Rollback
    public void 아이템_삭제() {

        // given
        Member owner = memberService.join(Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build());

        Item item = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .owner(owner)
                .build();

        Item savedItem = itemService.addItem(item);

        // when
        itemService.deleteItem(savedItem.getId());

        // then
        assertThat(savedItem.getIsDeleted()).isTrue();
    }

    @Test
    public void 아이템_전체_조회(){

        // given
        Member owner = memberService.join(Member.builder()
                .email("testEmail")
                .password("testPassword")
                .build());

        Item item1 = Item.builder()
                .title("아이템1")
                .description("아이템1 설명")
                .category("의류")
                .price(10000)
                .owner(owner)
                .build();

        Item item2 = Item.builder()
                .title("아이템2")
                .description("아이템2 설명")
                .category("가전")
                .price(20000)
                .owner(owner)
                .build();

        Item savedItem1 = itemService.addItem(item1);
        Item savedItem2 = itemService.addItem(item2);

        // when
        List<Item> items = itemService.findAllItems();

        // then
        assertThat(items).contains(savedItem1, savedItem2);
    }

}
