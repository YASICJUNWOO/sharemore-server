package sharemore.sharemoreserver.domain.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sharemore.sharemoreserver.domain.item.Item;
import sharemore.sharemoreserver.domain.item.ItemService;
import sharemore.sharemoreserver.domain.item.dto.ItemRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/add")
    public ResponseEntity<Item> addItem(@RequestBody ItemRequest request) {
        Item savedItem = itemService.addItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

}
