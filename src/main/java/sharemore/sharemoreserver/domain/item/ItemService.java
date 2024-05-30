package sharemore.sharemoreserver.domain.item;

import sharemore.sharemoreserver.domain.item.dto.ItemRequest;

public interface ItemService {

    Item addItem(ItemRequest request);

    Item addItem(Item item);

    Item findItemById(Long id);

    Item updateItem(Long id, ItemRequest updatedItem);

    Item updateItem(Long id, Item updatedItem);
}
