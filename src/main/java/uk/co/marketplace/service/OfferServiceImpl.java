package uk.co.marketplace.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Offer;
import uk.co.marketplace.domain.Seller;
import uk.co.marketplace.repository.OfferRepository;
import uk.co.marketplace.repository.SellerRepository;

import java.util.List;

@Component("offerService")
@Transactional
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Offer addOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public List<Offer> getBySellerId(Integer sellerId) {
        return offerRepository.findByUser(sellerId);
    }

    @Override
    public Integer getCurrentOfferPriceOfItem(String itemId) {
        return offerRepository.findMinimumOfferPriceOfItem(itemId);
    }

    @Override
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }
}
