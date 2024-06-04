package sharemore.sharemoreserver.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final MemberService memberService;

    @Override
    public Item addItem(ItemRequest request) {
        Member byEmail = memberService.findByEmail(request.getOwnerEmail());
        Item item = request.toEntity(request, byEmail);
        return addItem(item);
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 존재하지 않습니다."));
    }


    @Override
    @Transactional
    public Item updateItem(Long id, ItemRequest updatedItem) {
        return updateItem(id, updatedItem.toEntity());
    }

    @Override
    @Transactional
    public Item updateItem(Long id, Item updatedItem) {
        Item item = findItemById(id);
        item.update(updatedItem);
        return item;
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        Item itemById = findItemById(id);
        itemById.delete();
    }

}
