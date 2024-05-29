package sharemore.sharemoreserver.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;
import sharemore.sharemoreserver.domain.member.Member;
import sharemore.sharemoreserver.domain.member.service.MemberService;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final MemberService memberService;

    public Item addItem(ItemRequest request) {
        Member byEmail = memberService.findByEmail(request.getOwnerEmail());
        Item item = request.toEntity(request, byEmail);
        return addItem(item);
    }

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    public Item findItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 존재하지 않습니다."));
    }
}
