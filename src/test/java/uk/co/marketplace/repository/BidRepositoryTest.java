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
import uk.co.marketplace.domain.Bid;
import uk.co.marketplace.domain.Buyer;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class BidRepositoryTest {

    @Autowired
    BidRepository bidRepository;

    @Autowired
    BuyerRepository buyerRepository;


    @Test
    public void testFindByUser() {
        Integer buyerId = new Integer("6");
        Buyer buyer = buyerRepository.findById(buyerId);
        assertThat(buyer.getId(), is(equalTo(buyerId)));
        Bid bid1 = new Bid();
        bid1.setUser(buyerId);
        bid1.setItemId("12345C");
        bid1.setPricePerUnit(new Integer("5"));
        bid1.setQuantity(new Integer("10"));
        Bid bidSaved1 = bidRepository.save(bid1);
        assertThat(bidSaved1.getId(), is(notNullValue()));
        assertThat(bidSaved1.getUser(),is(buyerId.intValue()));

        Bid bid2 = new Bid();
        bid2.setUser(buyerId);
        bid2.setItemId("2345");
        bid2.setPricePerUnit(new Integer("8"));
        bid2.setQuantity(new Integer("8"));
        Bid bidSaved2 = bidRepository.save(bid2);
        assertThat(bidSaved2.getId(), is(notNullValue()));
        assertThat(bidSaved2.getUser(),is(6));

        List<Bid> emptyList = bidRepository.findByUser(new Integer("3"));
        assertThat(emptyList, hasSize(0));

        List<Bid> bidList = bidRepository.findByUser(buyerId);
        assertThat(bidList, hasSize(2));

    }

    @Test
    public void testFindByMaximumBidPriceItem() {

        Bid bid1 = new Bid();
        bid1.setUser(new Integer("1"));
        bid1.setItemId("12345C");
        bid1.setPricePerUnit(new Integer("5"));
        bid1.setQuantity(new Integer("10"));
        bidRepository.save(bid1);
        Bid bid2 = new Bid();
        bid2.setUser(new Integer("2"));
        bid2.setItemId("12345C");
        bid2.setPricePerUnit(new Integer("8"));
        bid2.setQuantity(new Integer("8"));
        bidRepository.save(bid2);
        Integer maxPrice = bidRepository.findByMaximumBidPriceItem("12345C");
        assertThat(maxPrice.intValue(),is(8));

    }

    @Test
    public void testDeleteByItemId () {
        Integer buyerId = new Integer("1");
        Bid bid1 = new Bid();
        bid1.setUser(buyerId);
        bid1.setItemId("12345C");
        bid1.setPricePerUnit(new Integer("5"));
        bid1.setQuantity(new Integer("10"));
        Bid bidSaved = bidRepository.save(bid1);
        assertThat(bidSaved.getId(), is(notNullValue()));
        assertThat(bidSaved.getUser(),is(buyerId.intValue()));
        Bid first = bidRepository.findById(bidSaved.getId());
        assertThat(first.getId(),is(bidSaved.getId()));
        bidRepository.deleteById(first.getId());
        assertThat(bidRepository.findById(first.getId()), is(nullValue()));
    }

    @Test
    public void testFindAll(){
        List<Bid> bids = bidRepository.findAll();
        assertThat(bids, hasSize(0));
        Integer buyerId = new Integer("2");
        Bid bid1 = new Bid();
        bid1.setUser(buyerId);
        bid1.setItemId("12345C");
        bid1.setPricePerUnit(new Integer("5"));
        bid1.setQuantity(new Integer("10"));
        Bid bidSaved = bidRepository.save(bid1);
        assertThat(bidSaved.getId(), is(notNullValue()));
        Bid bid2 = new Bid();
        bid2.setUser(buyerId);
        bid2.setItemId("12345C");
        bid2.setPricePerUnit(new Integer("5"));
        bid2.setQuantity(new Integer("10"));
        Bid bidSaved2 = bidRepository.save(bid2);
        assertThat(bidSaved2.getId(), is(notNullValue()));
        List<Bid> bidList = bidRepository.findAll();
        assertThat(bidList, hasSize(2));
    }

    @Test
    public void testFindByPricePerUnit() {

        Bid bid1 = new Bid();
        bid1.setUser(new Integer("2"));
        bid1.setItemId("12345C");
        bid1.setPricePerUnit(new Integer("5"));
        bid1.setQuantity(new Integer("10"));
        Bid bidSaved = bidRepository.save(bid1);
        assertThat(bidSaved.getId(), is(notNullValue()));
        Bid bid2 = new Bid();
        bid2.setUser(new Integer("4"));
        bid2.setItemId("12345C");
        bid2.setPricePerUnit(new Integer("6"));
        bid2.setQuantity(new Integer("10"));
        Bid bidSaved2 = bidRepository.save(bid2);
        assertThat(bidSaved2.getId(), is(notNullValue()));
        Bid bid3 = new Bid();
        bid3.setUser(new Integer("4"));
        bid3.setItemId("12345C");
        bid3.setPricePerUnit(new Integer("6"));
        bid3.setQuantity(new Integer("23"));
        Bid bidSaved3 = bidRepository.save(bid3);
        assertThat(bidSaved3.getId(), is(notNullValue()));

        List<Bid> bidList = bidRepository.findByPricePerUnit(new Integer("6"));
        assertThat(bidList, hasSize(2));
    }

    @Test
    public void testFindByItemId() {
        String itemId = "12345C";

        Bid bid1 = new Bid();
        bid1.setUser(new Integer("2"));
        bid1.setItemId(itemId);
        bid1.setPricePerUnit(new Integer("5"));
        bid1.setQuantity(new Integer("10"));
        Bid bidSaved = bidRepository.save(bid1);
        assertThat(bidSaved.getId(), is(notNullValue()));

        Bid bid2 = new Bid();
        bid2.setUser(new Integer("4"));
        bid2.setItemId(itemId);
        bid2.setPricePerUnit(new Integer("6"));
        bid2.setQuantity(new Integer("10"));
        Bid bidSaved2 = bidRepository.save(bid2);
        assertThat(bidSaved2.getId(), is(notNullValue()));

        Bid bid3 = new Bid();
        bid3.setUser(new Integer("4"));
        bid3.setItemId(itemId);
        bid3.setPricePerUnit(new Integer("6"));
        bid3.setQuantity(new Integer("23"));
        Bid bidSaved3 = bidRepository.save(bid3);
        assertThat(bidSaved3.getId(), is(notNullValue()));

        List<Bid> bidList = bidRepository.findByItemId(itemId);
        assertThat(bidList, hasSize(3));
        assertThat(bidList, contains(bid1, bid2, bid3));

    }

}
