package pl.wrydz.sklep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wrydz.sklep.entity.Basket;
import pl.wrydz.sklep.entity.BasketItem;
import pl.wrydz.sklep.repository.BasketItemRepo;
import pl.wrydz.sklep.repository.BasketRepo;

import java.util.List;

@Service
public class BasketItemService {

    private BasketItemRepo basketItemRepo;
    private ProductService productService;
    private BasketRepo basketRepo;

    @Autowired
    public BasketItemService(BasketItemRepo basketItemRepo, BasketRepo basketRepo, ProductService productService) {
        this.basketItemRepo = basketItemRepo;
        this.basketRepo = basketRepo;
        this.productService = productService;
    }

    public BasketItem addToBasket(long userId, long productId) {
        Basket basket = basketRepo.findBasketByUser(userId);
        BasketItem item = getItemFromBasket(basket.getId(), productId);
        if (item == null) {
            item = new BasketItem();
            item.setBasket(basket);
            item.setProduct(productService.getProduct(productId));
            item.setQuantity(1);
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
        basketItemRepo.save(item);
        return item;
    }

    public BasketItem removeFromBasket(long userId, long productId) {
        Basket basket = basketRepo.findBasketByUser(userId);
        BasketItem item = getItemFromBasket(basket.getId(), productId);
        if (item != null) {
            if (item.getQuantity() == 1) {
                basketItemRepo.delete(item);
            } else {
                item.setQuantity(item.getQuantity() - 1);
                basketItemRepo.save(item);
            }
        }
        return item;
    }

    private BasketItem getItemFromBasket(long basketId, long productId) {
        return basketItemRepo.getItemFromBasket(basketId, productId);
    }

    double getBasketTotalPrice(long basketId) {
        List<BasketItem> items = basketItemRepo.findBasketItemsByBasket(basketId);
        return items.stream().mapToDouble(BasketItem::getTotalPrice).sum();
    }
//    double calculatePrice(BasketItem item){
//
//    }

}
