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
import uk.co.marketplace.service.BidService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BidControllerTest extends ControllerBaseTest {

    @Autowired
    private BidService bidService;


    @Test
    public void testAddBid() throws Exception {
        Bid bid = new Bid();
        bid.setUser(1);
        bid.setItemId("12345");
        bid.setPricePerUnit(5);
        bid.setQuantity(10);

        ObjectMapper mapper = new ObjectMapper();
        String object = mapper.writeValueAsString(bid);
        mockMvc.perform(post("/bid").param("bid",object)).andDo(print()).andExpect(status().isCreated());
    }


    @Test
    public void testGetBidsForUser() throws Exception {
        Bid bid1 = new Bid();
        bid1.setUser(1);
        bid1.setItemId("12345");
        bid1.setPricePerUnit(6);
        bid1.setQuantity(7);
        bidService.addBid(bid1);

        Bid bid2 = new Bid();
        bid2.setUser(1);
        bid2.setItemId("12345");
        bid2.setPricePerUnit(5);
        bid2.setQuantity(10);
        bidService.addBid(bid2);

        mockMvc.perform(get("/bid/user/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetCurrentPriceForItem() throws Exception {
        Bid bid1 = new Bid();
        bid1.setUser(1);
        bid1.setItemId("12345");
        bid1.setPricePerUnit(6);
        bid1.setQuantity(7);
        bidService.addBid(bid1);

        Bid bid2 = new Bid();
        bid2.setUser(1);
        bid2.setItemId("12345");
        bid2.setPricePerUnit(4);
        bid2.setQuantity(10);
        bidService.addBid(bid2);

        mockMvc.perform(get("/bid/price/12345")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string("6"));
    }

}
