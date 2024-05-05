package heesu.me.springjpademo.repository;

import heesu.me.springjpademo.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    // @PersistenceContext 생략 가능 (spring-data-jpa에서 지원)
    private final EntityManager em; // @PersistenceUnit을 사용하면 EntityMangerFactory를 주입 받을 수 있음

    public void save(Item item){
        if(item.getId() == null){
            em.persist(item);
        }else{
            em.merge(item);
        }
    }
    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
