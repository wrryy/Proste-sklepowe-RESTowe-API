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
import pl.wrydz.sklep.entity.BasketItem;
import pl.wrydz.sklep.entity.Product;
import pl.wrydz.sklep.entity.User;
import pl.wrydz.sklep.repository.BasketItemRepo;
import pl.wrydz.sklep.repository.BasketRepo;
import pl.wrydz.sklep.repository.ProductRepo;
import pl.wrydz.sklep.repository.UserRepo;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketItemServiceTest {

    @Autowired
    private BasketItemRepo basketItemRepo;
    @Autowired
    private BasketRepo basketRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    ProductRepo productRepo;
    @MockBean
    private ProductService productService;
    private BasketItemService service;
    private Basket basket;
    private Product product;
    private BasketItem item;

    private static long ID = 1L;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new BasketItemService(basketItemRepo, basketRepo, productService);
        User user = new User();
        user.setId(ID);
        user.setLogin("test");
        userRepo.saveAndFlush(user);
        basket = new Basket();
        basket.setId(ID);
        basket.setUser(user);
        basketRepo.saveAndFlush(basket);
        product = new Product();
        product.setId(ID);
        product.setName("test");
        product.setPrice(2.0);
        productRepo.saveAndFlush(product);
    }

    @Test
    public void shouldIncreaseItemQuantity() {
        item = new BasketItem();
        item.setId(ID);
        item.setBasket(basket);
        item.setProduct(product);
        basketItemRepo.saveAndFlush(item);

        BasketItem result = service.addToBasket(ID, ID);

        Assert.assertEquals(1, result.getQuantity());
    }

    @Test
    public void shouldCreateItemW() {
        Mockito.when(productService.getProduct(ID)).thenReturn(product);

        BasketItem result = service.addToBasket(ID, ID);

        Assert.assertNotNull(result);
        Assert.assertEquals(product.getName(), result.getProduct().getName());
        Assert.assertEquals(1, result.getQuantity());
    }

    @Test
    public void shouldDecreaseItemQuantity() {
        item = new BasketItem();
        item.setId(ID);
        item.setBasket(basket);
        item.setQuantity(2);
        item.setProduct(product);
        basketItemRepo.saveAndFlush(item);

        BasketItem result = service.removeFromBasket(ID, ID);

        Assert.assertEquals(1, result.getQuantity());
    }
}