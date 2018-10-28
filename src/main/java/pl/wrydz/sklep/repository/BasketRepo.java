package pl.wrydz.sklep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wrydz.sklep.entity.Basket;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Long> {

    @Query(nativeQuery = true, value = "select * from baskets where user_id=:id and closed=0")
    Basket findBasketByUser(@Param("id") long id);

}
