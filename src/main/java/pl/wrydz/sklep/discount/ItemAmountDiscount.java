package pl.wrydz.sklep.discount;

import pl.wrydz.sklep.entity.BasketItem;

import java.util.List;

public class ItemAmountDiscount implements Discount {

    private long productId;
    private int quantity;
    private double price;

    public ItemAmountDiscount(long productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public void apply(List<BasketItem> items) {
        if (isApplicable(items)) {
            BasketItem item = items.stream().filter(i -> i.getProduct().getId() == productId).findAny().get();
            int discountItems = item.getQuantity() / this.quantity;
            double itemTotalPrice = discountItems * this.price + (item.getQuantity()-discountItems) * item.getProduct().getPrice();
            item.setTotalPrice(itemTotalPrice);
        }
    }

    /**
     * Check if discount is applicable to {@code BasketItem's}.
     *
     * @param items {@code List<BasketItem>}
     * @return boolean T
     */
    @Override
    public boolean isApplicable(List<BasketItem> items) {
        return items.stream().anyMatch(item -> item.getProduct().getId() == productId && item.getQuantity() >= quantity);
    }
}
