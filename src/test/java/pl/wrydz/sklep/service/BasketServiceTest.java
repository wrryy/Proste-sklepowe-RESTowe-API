package pl.wrydz.sklep.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.entity.User;
import pl.wrydz.sklep.repository.BasketRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketServiceTest {

    @MockBean
    private BasketRepo repo;
    @MockBean
    private BasketItemService itemService;
    @Autowired
    BasketService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkoutBasket() {
        Basket basket = new Basket();
        basket.setId(1L);
        basket.setUser(new User());
        basket.setClosed(false);
        Mockito.when(repo.getOne(1L)).thenReturn(basket);
        Mockito.when(itemService.getBasketTotalPrice(1L)).thenReturn(2.0);
        Basket result = service.checkoutBasket(1L);

        Assert.assertNotNull(result.getUser());
        Assert.assertEquals(2.0, result.getBasketPrice(), 2);
        Assert.assertTrue(result.isClosed());
    }
}