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
import uk.co.marketplace.domain.Bid;
import uk.co.marketplace.service.BidService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/bid")
public class BidController {

    @Autowired
    private BidService bidService;

    /**
     * Add a bid
     * @param strBid
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "",
            method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addBid(@RequestParam(name="bid") String strBid, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Bid bid = mapper.readValue(strBid, Bid.class);
        Bid savedBid = bidService.addBid(bid);
        response.setHeader("Location", request.getRequestURL().append("/").append(savedBid.getId()).toString());

    }

    /**
     * List bids for a given user id
     * @param buyerId
     * @return
     */
    @RequestMapping(value = "/user/{user}",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Bid> getBidsForUser(@PathVariable("user") int buyerId) {
        return bidService.getBidByBuyer(buyerId);
    }

    /**
     * Get a current price for item
     * @param itemId
     * @return  price for the item
     */
    @RequestMapping(value = "/price/{item}",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public int getCurrentPriceForItem(@PathVariable("item") String itemId){
        return bidService.getCurrentBidPriceOfItem(itemId).intValue();
    }

}
