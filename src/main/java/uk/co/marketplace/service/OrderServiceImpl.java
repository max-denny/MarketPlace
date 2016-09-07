package uk.co.marketplace.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Bid;
import uk.co.marketplace.domain.ItemOrder;
import uk.co.marketplace.domain.Offer;
import uk.co.marketplace.repository.BidRepository;
import uk.co.marketplace.repository.ItemOrderRepository;
import uk.co.marketplace.repository.OfferRepository;


import java.util.List;

@Component("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemOrderRepository orderRepository;
    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private BidRepository bidRepository;


    @Override
    public ItemOrder addOrder(ItemOrder itemOrder) {
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

    @Override
    public boolean createOrder(String itemId){
        Offer offer = getFirstMatchingOffer(itemId);
        Bid bid = getFirstMatchingBid(itemId, offer.getQuantity(), offer.getPricePerUnit());

        if(offer != null && bid != null) {
            ItemOrder item = new ItemOrder();
            item.setItemId(itemId);
            item.setBuyerId(bid.getUser());
            item.setSellerId(offer.getUser());
            item.setPricePerUnit(offer.getPricePerUnit());
            item.setQuantity(bid.getQuantity());
            orderRepository.save(item);

            int remainder = offer.getQuantity() - bid.getQuantity();
            bidRepository.deleteById(bid.getId());

            if(remainder > 0 ) {
                offer.setQuantity(remainder);
                offerRepository.save(offer);
            } else {
                offerRepository.deleteById(offer.getId());
            }
            return true;
        }
        return false;
    }

    private Offer getFirstMatchingOffer(String itemId){
        List<Offer> offerList = offerRepository.findByItemId(itemId);
        return offerList.stream().findFirst().orElse(null);
    }

    private Bid getFirstMatchingBid(String itemId, Integer quantity, Integer price) {
        List<Bid> bidList = bidRepository.findByItemId(itemId);
        return bidList.stream().
                filter( x -> x.getQuantity() <= quantity && x.getPricePerUnit() >=  price).
                findFirst().orElse(null);
    }


}
