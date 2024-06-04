package sharemore.sharemoreserver.domain.item.service;

import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;

public interface ItemService {

    Item addItem(ItemRequest request);

    Item addItem(Item item);

    Item findItemById(Long id);

    Item updateItem(Long id, ItemRequest updatedItem);

    Item updateItem(Long id, Item updatedItem);

    void deleteItem(Long id);
}
