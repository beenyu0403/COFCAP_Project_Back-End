package proj.been433.cofcapshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import proj.been433.cofcapshop.api.CoffeeListResponse;
import proj.been433.cofcapshop.item.Coffee;
import proj.been433.cofcapshop.item.Description;
import proj.been433.cofcapshop.item.Item;
import proj.been433.cofcapshop.repository.ItemRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }
    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(price);
        findItem.setName(name);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() { return itemRepository.findAll();}

    public List<Coffee> findItemsByKeyword(String keyword) { return itemRepository.findAllByKeyword(keyword);}

    public Item findOne(Long itemId) {return itemRepository.findOne(itemId);}
}

