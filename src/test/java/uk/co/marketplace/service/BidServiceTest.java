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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Bid;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class BidServiceTest {

    @Autowired
    private BidService bidService;


    @Test
    public void testAddBid() {
        Bid bid = new Bid();
        bid.setUser(new Integer("1"));
        bid.setQuantity(new Integer("4"));
        bid.setPricePerUnit(new Integer("5"));
        bid.setItemId("1234C");
        Bid bidAdded = bidService.addBid(bid);
        assertThat(bidAdded.getId(), not(nullValue()));
        assertThat(bidAdded.getItemId(), is("1234C"));
    }


    @Test
    public void testGetBidByBuyer() {
        Bid bid = new Bid();
        bid.setUser(new Integer("2"));
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit((new Integer("5")));
        bid.setItemId("1234C");
        Bid bidAdded = bidService.addBid(bid);
        assertThat(bidAdded.getId(), not(nullValue()));

        Bid bid1 = new Bid();
        bid1.setUser(new Integer("2"));
        bid1.setQuantity(new Integer("3"));
        bid1.setPricePerUnit((new Integer("5")));
        bid1.setItemId("1234C");
        Bid bidAdded1 = bidService.addBid(bid1);
        assertThat(bidAdded1.getId(), not(nullValue()));

        List<Bid> bidList = bidService.getBidByBuyer(new Integer("2"));
        assertThat(bidList, hasSize(2));

    }


    @Test
    public void testGetCurrentBidPriceOfItem(){
        Bid bid = new Bid();
        bid.setUser(new Integer("2"));
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit((new Integer("5")));
        bid.setItemId("1234C");
        Bid bidAdded = bidService.addBid(bid);
        assertThat(bidAdded.getId(), not(nullValue()));

        Bid bid1 = new Bid();
        bid1.setUser(new Integer("3"));
        bid1.setQuantity(new Integer("3"));
        bid1.setPricePerUnit((new Integer("6")));
        bid1.setItemId("1234C");
        Bid bidAdded1 = bidService.addBid(bid1);
        assertThat(bidAdded1.getId(), not(nullValue()));

        Integer price = bidService.getCurrentBidPriceOfItem("1234C");
        assertThat(price, is(6));

        List<Bid> bidList = bidService.getAllBidsWithCurrentPrice(new Integer("6"));
        assertThat(bidList, hasSize(1));
    }

    @Test
    public void testDeleteBidById() {
        Bid bid = new Bid();
        bid.setUser(new Integer("2"));
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit((new Integer("5")));
        bid.setItemId("1234C");
        Bid bidAdded = bidService.addBid(bid);
        assertThat(bidAdded.getId(), not(nullValue()));
        int a = bidAdded.getId();
        bidService.deleteBidById(bidAdded.getId());
        List<Bid> bidList = bidService.getAllBids();
        Bid result = bidList.stream().filter(b -> b.getId() == a).
                map(Optional::ofNullable).findAny().
                flatMap(Function.identity()).orElse(null);
        assertThat(result, is(nullValue()));
    }


    @Test
    public void testGetAllBids() {

        List<Bid> bidEmpty = bidService.getAllBids();
        assertThat(bidEmpty, hasSize(0));

        Bid bid = new Bid();
        bid.setUser(new Integer("1"));
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit((new Integer("5")));
        bid.setItemId("1234A");
        Bid bidAdded = bidService.addBid(bid);
        assertThat(bidAdded.getId(), not(nullValue()));

        Bid bid1 = new Bid();
        bid1.setUser(new Integer("2"));
        bid1.setQuantity(new Integer("4"));
        bid1.setPricePerUnit((new Integer("6")));
        bid1.setItemId("1234B");
        Bid bidAdded1 = bidService.addBid(bid1);
        assertThat(bidAdded1.getId(), not(nullValue()));

        Bid bid2 = new Bid();
        bid2.setUser(new Integer("3"));
        bid2.setQuantity(new Integer("3"));
        bid2.setPricePerUnit((new Integer("7")));
        bid2.setItemId("1234E");
        Bid bidAdded2 = bidService.addBid(bid2);
        assertThat(bidAdded2.getId(), not(nullValue()));

        Bid bid3 = new Bid();
        bid3.setUser(new Integer("4"));
        bid3.setQuantity(new Integer("2"));
        bid3.setPricePerUnit((new Integer("8")));
        bid3.setItemId("1234E");
        Bid bidAdded3 = bidService.addBid(bid3);
        assertThat(bidAdded3.getId(), not(nullValue()));

        List<Bid> bidList = bidService.getAllBids();
        assertThat(bidList, hasSize(4));
        Double average = bidList.stream().filter(b -> b.getPricePerUnit() > 0).
                mapToInt(Bid::getPricePerUnit).average().getAsDouble();
        assertThat(average, is(6.5));
    }


}
