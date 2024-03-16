package proj.been433.cofcapshop.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import proj.been433.cofcapshop.api.CoffeeListResponse;
import proj.been433.cofcapshop.item.Coffee;
import proj.been433.cofcapshop.item.Item;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

    public List<Coffee> findAllByKeyword(String keyword) {
       // return em.createQuery("select c.name, c.imageName, c.price, c.description from Coffee c where c.name like concat('%', :keyword, '%')", CoffeeListResponse.class)
        return em.createQuery("select c from Coffee c where c.name like concat('%', :keyword, '%')", Coffee.class)
                .setParameter("keyword", keyword)
                .getResultList();
    }

}
//select user.id as id, user.name as name, user.phone as phone, user.dept_id as deptId, dept.name as deptName
