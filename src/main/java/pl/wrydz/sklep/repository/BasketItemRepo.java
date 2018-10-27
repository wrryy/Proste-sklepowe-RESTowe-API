package pl.wrydz.sklep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.wrydz.sklep.entity.BasketItem;

import java.util.List;

@Repository
public interface BasketItemRepo extends JpaRepository<BasketItem, Long> {

    @Query(nativeQuery = true, value = "select * from baskets_items where basket_id=:id")
    List<BasketItem> findBasketItemsByBasket(@Param("id") long id);

    @Query(nativeQuery = true, value = "select * from baskets_items where basket_id=:basket_id and product_id=:product_id")
    BasketItem getItemFromBasket(@Param("basket_id") long basketId, @Param("product_id") long productId);

}
