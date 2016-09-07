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
import org.springframework.test.web.servlet.MvcResult;
import uk.co.marketplace.domain.Offer;
import uk.co.marketplace.service.OfferService;

import static org.mockito.Matchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class OfferControllerTest extends ControllerBaseTest {

    @Autowired
    private OfferService offerService;

    @Test
    public void testAddOffer() throws Exception {
        Offer offer = new Offer();
        offer.setItemId("12345");
        offer.setPricePerUnit(8);
        offer.setQuantity(2);
        offer.setUser(5);

        ObjectMapper mapper = new ObjectMapper();
        String object = mapper.writeValueAsString(offer);
        mockMvc.perform(post("/offer").param("offer",object)).andDo(print()).andExpect(status().isCreated());

    }

    @Test
    public void testGetOffersForUser() throws Exception {

        Offer offer1 = new Offer();
        offer1.setItemId("12345");
        offer1.setPricePerUnit(8);
        offer1.setQuantity(2);
        offer1.setUser(6);
        offerService.addOffer(offer1);

        Offer offer2 = new Offer();
        offer2.setItemId("12345");
        offer2.setPricePerUnit(7);
        offer2.setQuantity(2);
        offer2.setUser(6);
        offerService.addOffer(offer2);

        String content = "[ {\n" +
                "  \"id\" : 1,\n" +
                "  \"user\" : 6,\n" +
                "  \"itemId\" : \"12345\",\n" +
                "  \"quantity\" : 2,\n" +
                "  \"pricePerUnit\" : 8\n" +
                "}, {\n" +
                "  \"id\" : 2,\n" +
                "  \"user\" : 6,\n" +
                "  \"itemId\" : \"12345\",\n" +
                "  \"quantity\" : 2,\n" +
                "  \"pricePerUnit\" : 7\n" +
                "} ]";

        mockMvc.perform(get("/offer/user/6")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string(content));


    }

    @Test
    public void testGetCurrentPriceForItem() throws Exception {

        Offer offer1 = new Offer();
        offer1.setItemId("12345");
        offer1.setPricePerUnit(8);
        offer1.setQuantity(2);
        offer1.setUser(6);
        offerService.addOffer(offer1);

        Offer offer2 = new Offer();
        offer2.setItemId("12345");
        offer2.setPricePerUnit(7);
        offer2.setQuantity(2);
        offer2.setUser(6);
        offerService.addOffer(offer2);

        mockMvc.perform(get("/offer/price/12345")).andDo(print()).
                andExpect(status().isOk()).
                andExpect(content().string("7"));

    }


}
