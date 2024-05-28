package sharemore.sharemoreserver.domain.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

}
