package uk.co.marketplace.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Bid;
import uk.co.marketplace.domain.ItemOrder;
import uk.co.marketplace.domain.Offer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;

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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OfferService offerService;
    @Autowired
    BidService bidService;

    @Test
    public void testAddOrder() {
        ItemOrder order = new ItemOrder();
        order.setBuyerId(new Integer("1"));
        order.setSellerId(new Integer("2"));
        order.setItemId("1234A");
        order.setQuantity(new Integer("3"));
        order.setPricePerUnit(new Integer("5"));
        ItemOrder itemOrder = orderService.addOrder(order);
        assertThat(itemOrder.getId(),is(notNullValue()));
    }

    @Test
    public void testGetByBuyerId() {
        Integer buyerId = new Integer("2");

        ItemOrder order1 = new ItemOrder();
        order1.setBuyerId(buyerId);
        order1.setSellerId(new Integer("3"));
        order1.setItemId("1234B");
        order1.setQuantity(new Integer("2"));
        order1.setPricePerUnit(new Integer("6"));
        ItemOrder itemOrder1 = orderService.addOrder(order1);
        assertThat(itemOrder1.getId(), is(notNullValue()));

        ItemOrder order2 = new ItemOrder();
        order2.setBuyerId(buyerId);
        order2.setSellerId(new Integer("1"));
        order2.setItemId("1234C");
        order2.setQuantity(new Integer("2"));
        order2.setPricePerUnit(new Integer("7"));
        ItemOrder itemOrder2 = orderService.addOrder(order2);
        assertThat(itemOrder2.getId(), is(notNullValue()));

        ItemOrder order3 = new ItemOrder();
        order3.setBuyerId(buyerId);
        order3.setSellerId(new Integer("1"));
        order3.setItemId("1234D");
        order3.setQuantity(new Integer("2"));
        order3.setPricePerUnit(new Integer("7"));
        ItemOrder itemOrder3 = orderService.addOrder(order3);
        assertThat(itemOrder3.getId(), is(notNullValue()));

        List<ItemOrder> list = orderService.getByBuyerId(buyerId);
        assertThat(list, hasSize(3));
        assertThat(list, contains(order1, order2, order3));
        assertThat(list.get(0), is(order1));
    }

    @Test
    public void testGetBySellerId() {
        Integer sellerId = new Integer("2");

        ItemOrder order1 = new ItemOrder();
        order1.setBuyerId(new Integer("1"));
        order1.setSellerId(sellerId);
        order1.setItemId("1234B");
        order1.setQuantity(new Integer("2"));
        order1.setPricePerUnit(new Integer("6"));
        ItemOrder itemOrder1 = orderService.addOrder(order1);
        assertThat(itemOrder1.getId(), is(notNullValue()));

        ItemOrder order2 = new ItemOrder();
        order2.setBuyerId(new Integer("3"));
        order2.setSellerId(sellerId);
        order2.setItemId("1234C");
        order2.setQuantity(new Integer("2"));
        order2.setPricePerUnit(new Integer("7"));
        ItemOrder itemOrder2 = orderService.addOrder(order2);
        assertThat(itemOrder2.getId(), is(notNullValue()));

        ItemOrder order3 = new ItemOrder();
        order3.setBuyerId(new Integer("4"));
        order3.setSellerId(sellerId);
        order3.setItemId("1234D");
        order3.setQuantity(new Integer("2"));
        order3.setPricePerUnit(new Integer("7"));
        ItemOrder itemOrder3 = orderService.addOrder(order3);
        assertThat(itemOrder3.getId(), is(notNullValue()));

        List<ItemOrder> list = orderService.getBySellerId(sellerId);
        assertThat(list, hasSize(3));
        assertThat(list, contains(order1, order2, order3));
        assertThat(list.get(0), is(order1));
    }

    @Test
    public void testCreateOrder0() {

        String itemId = "1234C";

        Offer offer = new Offer();
        offer.setQuantity(new Integer("5"));
        offer.setPricePerUnit(new Integer("2"));
        offer.setItemId(itemId);
        offer.setUser(new Integer("1"));
        Offer savedOffer = offerService.addOffer(offer);
        assertThat(savedOffer.getId(), is(notNullValue()));

        Bid bid = new Bid();
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit(new Integer("2"));
        bid.setItemId(itemId);
        bid.setUser(new Integer("2"));
        Bid savedBid = bidService.addBid(bid);
        assertThat(savedBid.getId(), is(notNullValue()));

        Boolean result = orderService.createOrder(itemId);
        assertThat(result,is(true));
        List<Offer> offerList = offerService.getBySellerId(new Integer("1"));
        assertThat(offerList, hasSize(0));

    }


    @Test
    public void testCreateOrder1() {

        String itemId = "1234C";

        Offer offer = new Offer();
        offer.setQuantity(new Integer("10"));
        offer.setPricePerUnit(new Integer("2"));
        offer.setItemId(itemId);
        offer.setUser(new Integer("1"));
        Offer savedOffer = offerService.addOffer(offer);
        assertThat(savedOffer.getId(), is(notNullValue()));

        Bid bid = new Bid();
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit(new Integer("2"));
        bid.setItemId(itemId);
        bid.setUser(new Integer("2"));
        Bid savedBid = bidService.addBid(bid);
        assertThat(savedBid.getId(), is(notNullValue()));

        Boolean result = orderService.createOrder(itemId);
        assertThat(result,is(true));
        List<Offer> offerList = offerService.getBySellerId(new Integer("1"));
        assertThat(offerList.get(0).getQuantity(), is(5));

    }

    @Test
    public void testCreateOrderNotMatch() {

        String itemId = "1234C";

        Offer offer = new Offer();
        offer.setQuantity(new Integer("3"));
        offer.setPricePerUnit(new Integer("2"));
        offer.setItemId(itemId);
        offer.setUser(new Integer("1"));
        Offer savedOffer = offerService.addOffer(offer);
        assertThat(savedOffer.getId(), is(notNullValue()));

        Bid bid = new Bid();
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit(new Integer("2"));
        bid.setItemId(itemId);
        bid.setUser(new Integer("2"));
        Bid savedBid = bidService.addBid(bid);
        assertThat(savedBid.getId(), is(notNullValue()));

        Boolean result = orderService.createOrder(itemId);
        assertThat(result,is(false));
    }


    @Test
    public void testCreateOrderMultipleOffer() {

        String itemId = "1234C";

        Offer offer = new Offer();
        offer.setQuantity(new Integer("10"));
        offer.setPricePerUnit(new Integer("2"));
        offer.setItemId(itemId);
        offer.setUser(new Integer("1"));
        Offer savedOffer = offerService.addOffer(offer);
        assertThat(savedOffer.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setQuantity(new Integer("3"));
        offer2.setPricePerUnit(new Integer("2"));
        offer2.setItemId(itemId);
        offer2.setUser(new Integer("1"));
        Offer savedOffer2 = offerService.addOffer(offer2);
        assertThat(savedOffer2.getId(), is(notNullValue()));

        Bid bid = new Bid();
        bid.setQuantity(new Integer("5"));
        bid.setPricePerUnit(new Integer("2"));
        bid.setItemId(itemId);
        bid.setUser(new Integer("2"));
        Bid savedBid = bidService.addBid(bid);
        assertThat(savedBid.getId(), is(notNullValue()));

        Boolean result = orderService.createOrder(itemId);
        assertThat(result,is(true));
        List<Offer> offerList = offerService.getBySellerId(new Integer("1"));
        assertThat(offerList, hasSize(2));
        assertThat(offerList.get(0).getQuantity(), is(5));

    }

}
