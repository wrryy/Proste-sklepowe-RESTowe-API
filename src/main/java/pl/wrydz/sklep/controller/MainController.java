package pl.wrydz.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.entity.BasketItem;
import pl.wrydz.sklep.entity.Product;
import pl.wrydz.sklep.service.BasketItemService;
import pl.wrydz.sklep.service.BasketService;
import pl.wrydz.sklep.service.ProductService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Scope("session")
@SessionAttributes({"userId", "basketId"})
public class MainController {

    private BasketService basketService;
    private BasketItemService basketItemService;
    private ProductService productService;

    @Autowired
    public MainController(HttpSession session, BasketService basketService, BasketItemService basketItemService, ProductService productService) {
        this.productService = productService;
        this.basketService = basketService;
        this.basketItemService = basketItemService;
        session.setAttribute("userId", 1L);
        productService.add(); //workaround for importing datasource
    }

    /**
     * Returns new user basket.
     * @param session HttpSession
     * @return {@code Basket}
     */
    @GetMapping("/baskets")
    public Basket getBasket(HttpSession session) {
        Basket basket = basketService.getBasket(getUserId(session));
        session.setAttribute("basketId", basket.getId());
        return basket;
    }

    /**
     * Returns total price of items in the basket.
     * @param session HttpSession
     * @return {@code Basket}
     */
    @PutMapping("/baskets")
    public Basket checkoutBasket(HttpSession session) {
        return basketService.checkoutBasket(getBasketId(session));
    }

    /**
     * Returns list of all shop products.
     * @return {@code Basket}
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    /**
     * Returns list of items in the basket.
     * @param session HttpSession
     * @return {@code List<BasketItem>}
     */
    @GetMapping("/items")
    public List<BasketItem> getAllItems(HttpSession session) {
        return basketItemService.findAllItemsByBasket(getBasketId(session));
    }

    /**
     * Put 1 product with {id} in the basket.
     * @param productId long
     * @param session HttpSession
     * @return {@code BasketItem}
     */
    @PostMapping("/items/{productId}")
    public BasketItem addItemToBasket(@PathVariable long productId, HttpSession session) {
        return basketItemService.addToBasket(getBasketId(session), productId);
    }

    /**
     * Removes 1 product with {id} from the basket.
     * @param productId long
     * @param session HttpSession
     * @return {@code BasketItem}
     */
    @PutMapping("/items/{productId}")
    public BasketItem removeItemToBasket(@PathVariable long productId, HttpSession session) {
        return basketItemService.removeFromBasket(getBasketId(session), productId);
    }

    private long getBasketId(HttpSession session) {
        return (long) session.getAttribute("basketId");
    }

    private long getUserId(HttpSession session) {
        return (long) session.getAttribute("userId");
    }

}
