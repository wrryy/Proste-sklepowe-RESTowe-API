package pl.wrydz.sklep.discount;

import pl.wrydz.sklep.entity.BasketItem;

import java.util.List;

/**
 * Interface enables validating and applying discount if there is/are proper items in the basket.
 */
interface Discount {

    /**
     * Contains discount logic and should set new price to the {@code BasketItem}
     * @param items {@code List<BasketItem>}
     */
    void apply(List<BasketItem> items);

    /**
     * Checks if discount conditions are met.
     * @param items {@code List<BasketItem>}
     * @return boolean
     */
    boolean isApplicable(List<BasketItem> items);
}
