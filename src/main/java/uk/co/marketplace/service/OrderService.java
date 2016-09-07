package uk.co.marketplace.service;


import uk.co.marketplace.domain.ItemOrder;

import java.util.List;

/**
 * ItemOrder Service
 */
public interface OrderService {


    /**
     * Add an itemOrder
     * @param itemOrder
     */
    ItemOrder addOrder(ItemOrder itemOrder);

    /**
     * List the orders for a given seller user ID
     * @param sellerId
     * @return list of orders for a user
     */
    List<ItemOrder> getBySellerId(Integer sellerId);

    /**
     * List the orders for a given buyer
     * @param buyerId
     * @return list of orders for a given user
     */
    List<ItemOrder> getByBuyerId(Integer buyerId);

    /**
     * Create an order by finding all the offers with the same itemId and choosing the first one,
     * then find all the bids with the same itemId that have a price less than or equal to the offer price
     * and have a quantity greater than or equal to the offer quantity.
     * Remove the bid and decrease the quantity in the offer by the number in the order.
     * @param itemId
     * @return a boolean that indicates the order was created
     */
    boolean createOrder(String itemId);
}
