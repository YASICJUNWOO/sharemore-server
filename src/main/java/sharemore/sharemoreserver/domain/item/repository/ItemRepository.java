package sharemore.sharemoreserver.domain.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharemore.sharemoreserver.domain.item.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
