package uk.co.marketplace.repository;

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

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Bid;


import java.util.List;

/**
 * The Bid repository
 */
public interface BidRepository extends CrudRepository<Bid, Integer> {


    /**
     * Add a bid to the database
     * @param bid
     * @return saved bid
     */
     Bid save(Bid bid);


    /**
     * List the bids for a given buyer id
     * @param buyerId
     * @return list of bids for that user
     */
    List<Bid> findByUser(Integer buyerId);


    /**
     * Retrieve the current bid price for a given item ID (i.e. the highest price of all bids for that item)
     * @param itemId
     * @return the maximum highest bid price for an item
     */
    @Query("SELECT MAX(pricePerUnit) FROM Bid WHERE ITEMID=?1")
    Integer findByMaximumBidPriceItem(String itemId);

    /**
     * Retrieve item by it's id
     * @param id
     * @return the bid with the id
     */
      Bid findById(Integer id);

    /**
     * Find all the bids with the given price per unit
     * @param price
     * @return list of bids with the given price per unit
     */
    List<Bid> findByPricePerUnit(Integer price);


    /**
     * Delete by Id
     * @param id
     */
     @Modifying
     @Transactional
     @Query("DELETE FROM Bid WHERE id=?1")
     void deleteById(Integer id);

    /**
     * Get all the bids in the database
     * @return list of bids
     */
     List<Bid> findAll();


    /**
     * Get all the bids in the database with the same itemId
     * @return list of bids with given itemId
     */
    List<Bid> findByItemId(String itemId);

}
