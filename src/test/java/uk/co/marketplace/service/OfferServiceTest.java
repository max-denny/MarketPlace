package uk.co.marketplace.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Offer;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
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
public class OfferServiceTest {

    @Autowired
    OfferService offerService;

    @Test
    public void testAddOffer() {
        Offer offer = new Offer();
        offer.setUser(new Integer("1"));
        offer.setItemId("234A");
        offer.setPricePerUnit(5);
        offer.setQuantity(10);
        Offer offerSaved = offerService.addOffer(offer);
        assertThat(offerSaved.getId(), is(notNullValue()));

    }

    @Test
    public void testGetBySellerId() {
        Integer sellerId = new Integer("1");
        Offer offer = new Offer();
        offer.setUser(sellerId);
        offer.setItemId("234A");
        offer.setPricePerUnit(5);
        offer.setQuantity(10);
        Offer offerSaved = offerService.addOffer(offer);
        assertThat(offerSaved.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(new Integer("2"));
        offer2.setItemId("234A");
        offer2.setPricePerUnit(6);
        offer2.setQuantity(11);
        Offer offerSaved2 = offerService.addOffer(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        Offer offer3 = new Offer();
        offer3.setUser(sellerId);
        offer3.setItemId("234B");
        offer3.setPricePerUnit(7);
        offer3.setQuantity(12);
        Offer offerSaved3 = offerService.addOffer(offer3);
        assertThat(offerSaved3.getId(), is(notNullValue()));

        List<Offer> offerList = offerService.getBySellerId(sellerId);
        assertThat(offerList, hasSize(2));

    }

    @Test
    public void testGetCurrentOfferPriceOfItem() {
        Offer offer = new Offer();
        offer.setUser(new Integer("1"));
        offer.setItemId("234A");
        offer.setPricePerUnit(5);
        offer.setQuantity(10);
        Offer offerSaved = offerService.addOffer(offer);
        assertThat(offerSaved.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(new Integer("1"));
        offer2.setItemId("234A");
        offer2.setPricePerUnit(6);
        offer2.setQuantity(11);
        Offer offerSaved2 = offerService.addOffer(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        Offer offer3 = new Offer();
        offer3.setUser(new Integer("1"));
        offer3.setItemId("234A");
        offer3.setPricePerUnit(7);
        offer3.setQuantity(12);
        Offer offerSaved3 = offerService.addOffer(offer3);
        assertThat(offerSaved3.getId(), is(notNullValue()));

        Integer result = offerService.getCurrentOfferPriceOfItem("234A");
        assertThat(result, is(5));

    }

    @Test
    public void testGetAllOffers() {

        Offer offer = new Offer();
        offer.setUser(new Integer("1"));
        offer.setItemId("234A");
        offer.setPricePerUnit(5);
        offer.setQuantity(10);
        Offer offerSaved = offerService.addOffer(offer);
        assertThat(offerSaved.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(new Integer("2"));
        offer2.setItemId("234B");
        offer2.setPricePerUnit(6);
        offer2.setQuantity(11);
        Offer offerSaved2 = offerService.addOffer(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        Offer offer3 = new Offer();
        offer3.setUser(new Integer("1"));
        offer3.setItemId("234C");
        offer3.setPricePerUnit(7);
        offer3.setQuantity(12);
        Offer offerSaved3 = offerService.addOffer(offer3);
        assertThat(offerSaved3.getId(), is(notNullValue()));

        Offer offer4 = new Offer();
        offer4.setUser(new Integer("2"));
        offer4.setItemId("234D");
        offer4.setPricePerUnit(5);
        offer4.setQuantity(12);
        Offer offerSaved4 = offerService.addOffer(offer4);
        assertThat(offerSaved4.getId(), is(notNullValue()));

        List<Offer> offerList = offerService.getAllOffers();
        assertThat(offerList, hasSize(4));

        Double average = offerList.stream().filter(o -> o.getPricePerUnit() > 0).
                mapToInt(Offer::getPricePerUnit).average().getAsDouble();
        assertThat(average, is(5.75));

    }

}
