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
import uk.co.marketplace.domain.Offer;

import java.util.List;

/**
 * Offer repository
 */
public interface OfferRepository extends CrudRepository<Offer, Integer> {


    /**
     * Add an offer
     * @param offer
     * @return saved offer
     */
     Offer save(Offer offer);

    /**
     * List the offers for a given seller
     * @param sellerId
     * @return list of offers for a given user
     */
    List<Offer> findByUser(Integer sellerId);

    /**
     * Retrieve the current offer price for a given item ID (i.e. the lowest price of all offers for that item)
     * @param itemId
     * @return lowest offer price for an item
     */
    @Query("SELECT MIN(pricePerUnit) FROM Offer WHERE ITEMID=?")
    Integer findMinimumOfferPriceOfItem(String itemId);

    /**
     * Find all the offers with a given price
     * @param price
     * @return List of all offers with a given price
     */
    List<Offer> findByPricePerUnit(Integer price);


    /**
     * Find an offer by it's id
     * @param id
     * @return the offer with the given id
     */
    Offer findById(Integer id);

    /**
     * Delete by Id
     * @param id
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Offer WHERE id=?1")
    void deleteById(Integer id);


    /**
     * Get all the offers in the database
     * @return list of offers
     */
    List<Offer> findAll();

}
