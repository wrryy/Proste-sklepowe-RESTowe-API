package pl.wrydz.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.repository.BasketRepo;

@Service
public class BasketService {

    private BasketRepo basketRepo;
    private UserService userService;
    private BasketItemService basketItemService;

    @Autowired
    public BasketService(BasketRepo basketRepo, UserService userService, BasketItemService basketItemService) {
        this.basketRepo = basketRepo;
        this.userService = userService;
        this.basketItemService = basketItemService;
    }

    public Basket getBasket(long userId) {
        Basket basket = basketRepo.findBasketByUser(userId);
        if (basket == null) {
            basket = new Basket();
            basket.setUser(userService.getUser(userId));
            basketRepo.save(basket);
        }
        return basket;
    }

    public Basket checkoutBasket(long userId) {
        Basket basket = basketRepo.findBasketByUser(userId);
        basket.setClosed(true);
        basket.setBasketPrice(basketItemService.getBasketTotalPrice(basket.getId()));
        basketRepo.save(basket);
        return basket;
    }

}
