package heesu.me.springjpademo.service;

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

    @Transactional(readOnly = true)
    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }

    @Transactional(readOnly = true)
    public List<Item> findItems(){
        return itemRepository.findAll();
    }

}
