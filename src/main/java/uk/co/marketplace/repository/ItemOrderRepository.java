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
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uk.co.marketplace.domain.ItemOrder;

import java.util.List;

/**
 * Represents an ItemOrder repository
 */
public interface ItemOrderRepository extends CrudRepository<ItemOrder, Integer> {


    /**
     * create an itemOrder
     * @param itemOrder
     */
    ItemOrder save(ItemOrder itemOrder);


    /**
     * List the orders for a given sellerId
     * @param sellerId
     * @return list the order for a given user (seller)
     */
    @Query("SELECT itemId, quantity, pricePerUnit, buyerId, sellerId FROM ItemOrder WHERE sellerId=?1")
    List<ItemOrder> findBySellerId(Integer sellerId);


    /**
     * List the orders for a given buyer
     * @param buyerId
     * @return List of orders for a given user (buyer)
     */
    @Query("SELECT itemId, quantity, pricePerUnit, buyerId, sellerId FROM ItemOrder WHERE buyerId=?1")
    List<ItemOrder> findByBuyerId(Integer buyerId);

}
