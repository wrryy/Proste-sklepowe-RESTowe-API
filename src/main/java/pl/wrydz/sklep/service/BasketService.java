package pl.wrydz.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.repository.BasketRepo;

@Service
public class BasketService {

    private UserService userService;
    private BasketRepo basketRepo;

    @Autowired
    public BasketService(UserService userService, BasketRepo basketRepo) {
        this.userService = userService;
        this.basketRepo = basketRepo;
    }

    public Basket getBasket(long userId){
        Basket basket = findBasketByUser(userId);
        if(basket == null){
            basket = new Basket();
            basket.setUser(userService.getUser(userId));
            basketRepo.save(basket);
            basketRepo.flush();
        }
        return basket;
    }

    public Basket checkoutBasket(long userId){
        Basket basket = findBasketByUser(userId);
        basket.setClosed(true);
        basketRepo.save(basket);
        return basket;
    }

    @Cacheable("baskets")
    public Basket findBasketByUser(long userId){
        return basketRepo.findBasketByUser(userId);
    }

}
