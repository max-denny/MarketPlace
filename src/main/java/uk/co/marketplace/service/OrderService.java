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
    ItemOrder createOrder(ItemOrder itemOrder);

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
}
