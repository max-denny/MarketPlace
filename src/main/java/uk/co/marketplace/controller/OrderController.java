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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uk.co.marketplace.domain.ItemOrder;
import uk.co.marketplace.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/seller/{sellerId}",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ItemOrder> getOrdersForSeller(@PathVariable("sellerId") int sellerId) {
        return orderService.getBySellerId(sellerId);
    }

    @RequestMapping(value = "/buyer/{buyerId}",
            method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ItemOrder> getOrdersForBuyer(@PathVariable("buyerId") int buyerId) {
        return orderService.getByBuyerId(buyerId);
    }


}
