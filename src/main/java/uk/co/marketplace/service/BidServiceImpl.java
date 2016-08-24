package uk.co.marketplace.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Bid;
import uk.co.marketplace.repository.BidRepository;
import uk.co.marketplace.repository.BuyerRepository;

import java.util.List;

@Component("bidService")
@Transactional
public class BidServiceImpl implements BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public Bid addBid(Bid bid) {
       return bidRepository.save(bid);
    }

    @Override
    public List<Bid> getBidByBuyer(Integer buyerId) {
        return bidRepository.findByUser(buyerId);
    }

    @Override
    public Integer getCurrentBidPriceOfItem(String itemId) {
        return bidRepository.findByMaximumBidPriceItem(itemId);
    }

    @Override
    public List<Bid> getAllBidsWithCurrentPrice(Integer price){
        return bidRepository.findByPricePerUnit(price);
    }

    @Override
    public void deleteBidById(Integer id) { bidRepository.deleteById(id);}

    @Override
    public List<Bid>  getAllBids() { return bidRepository.findAll();}
}
