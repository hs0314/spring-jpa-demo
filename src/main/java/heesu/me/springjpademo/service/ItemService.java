package heesu.me.springjpademo.service;

import heesu.me.springjpademo.domain.item.Book;
import heesu.me.springjpademo.domain.item.Item;
import heesu.me.springjpademo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // class레벨에서 readOnly=true 설정했기 때문에 dml은 @Transactional을 다시 붙여서 default인 readOnly=false셋팅
    public void save(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item getItem = itemRepository.findOne(itemId);

        getItem.setName(name);
        getItem.setPrice(price);
        getItem.setStockQuantity(stockQuantity);

        // 트랜잭션 commit이 되면 JPA에서 flush를 통해서 변경감지, update 쿼리 실행
    }

    @Transactional(readOnly = true)
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional(readOnly = true)
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

}
