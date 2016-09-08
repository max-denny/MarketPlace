package uk.co.marketplace.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import uk.co.marketplace.domain.Bid;
import uk.co.marketplace.domain.ItemOrder;
import uk.co.marketplace.domain.Offer;
import uk.co.marketplace.service.BidService;
import uk.co.marketplace.service.OfferService;
import uk.co.marketplace.service.OrderService;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class OrderControllerTest extends ControllerBaseTest {

    @Autowired
    OrderService orderService;
    @Autowired
    BidService bidService;
    @Autowired
    OfferService offerService;

    @Test
    public void testGetBySellerId() throws Exception{

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

        mockMvc.perform(get("/order/seller/2")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(containsString("\"itemId\" : \"1234D\"")));
    }

    @Test
    public void testGetByBuyerId() throws Exception{

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

        mockMvc.perform(get("/order/buyer/2")).
                andDo(print()).
                andExpect(status().isOk()).andExpect(content().string(containsString("itemId\" : \"1234B\"")));
    }

    @Test
    public void testCreateOrder() throws Exception{

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

        mockMvc.perform(post("/order").param("itemId",itemId)).andDo(print()).andExpect(status().isCreated());
    }


}
