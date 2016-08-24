package uk.co.marketplace.service;

 /*******************************************************************************************
 /*Copyright 2016 O Fadero
 /*
 /*Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 /*except in compliance with the License. You may obtain a copy of the License at
 /*
 /*    http://www.apache.org/licenses/LICENSE-2.0
 /*
 /*Unless required by applicable law or agreed to in writing, software distributed under the
 /* License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 /* either express or implied. See the License for the specific language governing permissions
 /* and limitations under the License.
 *********************************************************************************************/

import uk.co.marketplace.domain.Bid;

import java.util.List;


/**
 * Bid service
 */
public interface BidService {


    /**
     * Add a bid
     * @param bid
     */
     Bid addBid(Bid bid);


    /**
     * List the bids for a given buyer
     * @param buyerId
     * @return  list of bids for a user
     */
    List<Bid> getBidByBuyer(Integer buyerId);

    /**
     * Retrieve the current bid price for a given item ID (i.e. the highest price of all bids for that item)
     * @param itemId
     * @return the highest price bid for an item
     */
    Integer getCurrentBidPriceOfItem(String itemId);


    /**
     * Get all the bids that have the current bid price for an item
     * @param price
     * @return List of Bids with the current(highest) price for an item
     */
     List<Bid> getAllBidsWithCurrentPrice(Integer price);


    /**
     * Delete a bid by its id
     * @param id
     */
    void deleteBidById(Integer id);


    /**
     * Get all bids in the database
     * @return List of all bids in database
     */
    List<Bid> getAllBids();


}
