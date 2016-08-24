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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.marketplace.domain.Offer;
import uk.co.marketplace.service.OfferService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    OfferService offerService;

    @RequestMapping(value = "",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addOffer(@RequestParam(name="offer")String offerStr, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Offer offer = mapper.readValue(offerStr, Offer.class);
        Offer savedOffer = offerService.addOffer(offer);
        response.setHeader("Location", request.getRequestURL().append("/").append(savedOffer.getId()).toString());

    }

    //List the offers for a given seller user ID
    @RequestMapping(value = "/user/{sellerId}",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Offer> getOffersForUser(@PathVariable("sellerId") int sellerId) {
        return offerService.getBySellerId(sellerId);
    }

    //Retrieve the current offer price for a given item ID
    @RequestMapping(value = "/price/{itemId}",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public int getCurrentPriceForItem(@PathVariable("itemId") String itemId){
        return offerService.getCurrentOfferPriceOfItem(itemId).intValue();
    }


}

