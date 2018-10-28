package pl.wrydz.sklep.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.entity.BasketItem;
import pl.wrydz.sklep.entity.Product;
import pl.wrydz.sklep.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@DataJpaTest
public class BasketItemRepoTest {

    @Autowired
    TestEntityManager entityManager;
    @Autowired
    BasketItemRepo repo;
    private static final long ID = 1L;
    private static final double TWO = 2.0;

    private BasketItem item;

    @Before
    public void setUp() {
        Product product = new Product();
        product.setName("test");
        product.setPrice(TWO);
        User user = new User();
        user.setLogin("test");
        Basket basket = new Basket();
        basket.setUser(user);
        item = new BasketItem();
        item.setTotalPrice(TWO);
        item.setQuantity(2);
        item.setBasket(basket);
        item.setProduct(product);
        entityManager.persist(product);
        entityManager.persist(user);
        entityManager.persist(basket);
        entityManager.persist(item);
    }

    @Test
    public void shouldReturnBasketItem() {
        BasketItem result = repo.getItemFromBasket(ID, ID);

        Assert.assertEquals(item.getQuantity(), result.getQuantity());
        Assert.assertEquals(item.getProduct().getPrice(), result.getProduct().getPrice(), 2);
        Assert.assertEquals(item.getBasket().getUser().getLogin(), result.getBasket().getUser().getLogin());
    }
}