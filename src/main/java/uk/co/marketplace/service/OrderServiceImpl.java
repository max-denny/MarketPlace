package uk.co.marketplace.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.ItemOrder;
import uk.co.marketplace.repository.ItemOrderRepository;

import java.util.List;

@Component("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemOrderRepository orderRepository;

    @Override
    public ItemOrder createOrder(ItemOrder itemOrder) {
        return orderRepository.save(itemOrder);
    }

    @Override
    public List<ItemOrder> getBySellerId(Integer sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public List<ItemOrder> getByBuyerId(Integer buyerId) {
        return orderRepository.findByBuyerId(buyerId);
    }
}
