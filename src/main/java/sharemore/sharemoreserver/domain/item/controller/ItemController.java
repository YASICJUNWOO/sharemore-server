package sharemore.sharemoreserver.domain.item.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable(name = "id") Long id) {
        Item item = itemService.findItemById(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable(name = "id") Long id, @RequestBody ItemRequest request) {
        Item updatedItem = itemService.updateItem(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable(name = "id") Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
