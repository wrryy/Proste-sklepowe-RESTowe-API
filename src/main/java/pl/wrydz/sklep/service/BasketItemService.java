package pl.wrydz.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.entity.BasketItem;
import pl.wrydz.sklep.repository.BasketItemRepo;

import java.util.List;

@Service
public class BasketItemService {

    private BasketItemRepo basketItemRepo;
    private ProductService productService;
    private BasketService basketService;

    @Autowired
    public BasketItemService(BasketItemRepo basketItemRepo, ProductService productService, BasketService basketService) {
        this.basketItemRepo = basketItemRepo;
        this.productService = productService;
        this.basketService = basketService;
    }

    public void addToBasket(long userId, long productId){
        Basket basket = basketService.findBasketByUser(userId);
        BasketItem item = getItemFromBasket(basket.getId(), productId);
        if(item == null){
            item = new BasketItem();
            item.setBasket(basket);
            item.setProduct(productService.getProduct(productId));
            item.setQuantity(1);
        } else {
            item.setQuantity(item.getQuantity()+1);
        }
    }

    public void removeFromBasket(long userId, long productId){
        Basket basket = basketService.findBasketByUser(userId);
        BasketItem item = getItemFromBasket(basket.getId(), productId);
        if(item.getQuantity() == 1){
            basketItemRepo.delete(item);
        } else {
            item.setQuantity(item.getQuantity()-1);
        }
    }

    private BasketItem getItemFromBasket(long basketId, long productId){
        return basketItemRepo.getItemFromBasket(basketId, productId);
    }
    public double getTotalPrice(long basketId){
        List<BasketItem> items = basketItemRepo.findBasketItemsByBasket(basketId);
        return items.stream().mapToDouble(BasketItem::getTotalPrice).sum();
    }

}
