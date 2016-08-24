package uk.co.marketplace.service;


import uk.co.marketplace.domain.Offer;

import java.util.List;

/**
 * Offer Service interface
 */
public interface OfferService {

    /**
     * Add an offer
     * @param offer
     */
    Offer addOffer(Offer offer);

    /**
     * List the offers for a given seller id
     * @param sellerId
     * @return list of offers by user
     */
    List<Offer> getBySellerId(Integer sellerId);



    /**
     * Retrieve the current offer price for a given item ID (i.e. the lowest price of all offers for that item)
     * @param itemId
     * @return the lowest price offered for an item
     */
    Integer getCurrentOfferPriceOfItem(String itemId);

    /**
     * Get all saved offers
     * @return a list of all offers in the database
     */
    List<Offer> getAllOffers();
}
