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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.marketplace.domain.Buyer;
import uk.co.marketplace.domain.ItemOrder;
import uk.co.marketplace.domain.Seller;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class ItemOrderRepositoryTest {

    @Autowired
    ItemOrderRepository orderRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    BuyerRepository buyerRepository;

    @Test
    public void testFindBySellerId() {
        Integer sellerId = new Integer("3");
        Seller seller = sellerRepository.findById(sellerId);
        assertThat(seller.getId(),is(equalTo(sellerId)));
        ItemOrder itemOrder1 = new ItemOrder();
        itemOrder1.setItemId("1344");
        itemOrder1.setBuyerId(new Integer("1"));
        itemOrder1.setPricePerUnit(new Integer("6"));
        itemOrder1.setQuantity(new Integer("2"));
        itemOrder1.setSellerId(sellerId);
        orderRepository.save(itemOrder1);
        ItemOrder itemOrder2 = new ItemOrder();
        itemOrder2.setItemId("1344");
        itemOrder2.setBuyerId(new Integer("2"));
        itemOrder2.setPricePerUnit(new Integer("6"));
        itemOrder2.setQuantity(new Integer("2"));
        itemOrder2.setSellerId(sellerId);
        orderRepository.save(itemOrder2);
        List<ItemOrder> itemOrderList = orderRepository.findBySellerId(sellerId);
        assertThat(itemOrderList, hasSize(2));

    }

    @Test
    public void testFindByBuyerId() {
        Integer buyerId = new Integer("4");
        Buyer buyer = buyerRepository.findById(buyerId);
        assertThat(buyer.getId(),is(equalTo(buyerId)));
        ItemOrder itemOrder1 = new ItemOrder();
        itemOrder1.setItemId("1344");
        itemOrder1.setBuyerId(buyer.getId());
        itemOrder1.setPricePerUnit(new Integer("6"));
        itemOrder1.setQuantity(new Integer("2"));
        itemOrder1.setSellerId(new Integer("1"));
        orderRepository.save(itemOrder1);
        ItemOrder itemOrder2 = new ItemOrder();
        itemOrder2.setItemId("1344");
        itemOrder2.setBuyerId(buyer.getId());
        itemOrder2.setPricePerUnit(new Integer("6"));
        itemOrder2.setQuantity(new Integer("2"));
        itemOrder2.setSellerId(new Integer("2"));
        orderRepository.save(itemOrder2);
        List<ItemOrder> itemOrderList = orderRepository.findByBuyerId(buyerId);
        assertThat(itemOrderList, hasSize(2));

    }
}
