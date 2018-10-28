package pl.wrydz.sklep.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
@SessionAttributes({"userId"})
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

    @GetMapping("/basket")
    public long getBasket(HttpSession session) {
        Basket basket = basketService.getBasket(getUserId(session));
        session.setAttribute("basket", basket);
        return basket.getId();
    }

    @PutMapping("/basket")
    public Basket checkoutBasket(@RequestParam long userId) {
        return basketService.checkoutBasket(userId);
    }

    @GetMapping("/items")
    public List<Product> getAllItems() {
        return productService.findAll();
    }

    @PostMapping("/items/{productId}")
    public BasketItem addItemToBasket(@PathVariable long productId, HttpSession session) {
        BasketItem item = basketItemService.addToBasket(getUserId(session), productId);
        return item;
    }

    @PutMapping("/items/{productId}")
    public BasketItem removeItemToBasket(@PathVariable long productId, HttpSession session) {
        return basketItemService.removeFromBasket(getUserId(session), productId);
    }


    private long getUserId(HttpSession session) {
        return (long) session.getAttribute("userId");
    }

    @RequestMapping("/test")
    public void getId() {
        productService.add();
    }
}
