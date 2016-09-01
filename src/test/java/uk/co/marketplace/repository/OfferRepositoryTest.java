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
import org.springframework.transaction.annotation.Transactional;
import uk.co.marketplace.domain.Offer;
import uk.co.marketplace.domain.Seller;

import java.util.List;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
@Transactional
public class OfferRepositoryTest {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Test
    public void testFindByUser() {
        Integer sellerId = new Integer("2");
        Seller seller = sellerRepository.findById(sellerId);
        assertThat(seller.getId(), equalTo(sellerId));
        Offer offer1 = new Offer();
        offer1.setUser(sellerId);
        offer1.setQuantity(new Integer("3"));
        offer1.setPricePerUnit(new Integer("4"));
        offer1.setItemId("1234");
        Offer offerSaved1 = offerRepository.save(offer1);
        assertThat(offerSaved1.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(sellerId);
        offer2.setQuantity(new Integer("2"));
        offer2.setPricePerUnit(new Integer("10"));
        offer2.setItemId("12345");
        Offer offerSaved2 = offerRepository.save(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        List<Offer> emptyList = offerRepository.findByUser(new Integer("1"));
        assertThat(emptyList, hasSize(0));
        List<Offer> sellerList = offerRepository.findByUser(sellerId);
        assertThat(sellerList, hasSize(2));

    }

    @Test
    public void testFindMinimumOfferPriceOfItem() {
        Offer offer1 = new Offer();
        offer1.setUser(new Integer("3"));
        offer1.setQuantity(new Integer("3"));
        offer1.setPricePerUnit(new Integer("4"));
        offer1.setItemId("1234");
        Offer offerSaved = offerRepository.save(offer1);
        assertThat(offerSaved.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(new Integer("4"));
        offer2.setQuantity(new Integer("2"));
        offer2.setPricePerUnit(new Integer("10"));
        offer2.setItemId("1234");
        Offer offerSaved2 = offerRepository.save(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        Integer minPrice = offerRepository.findMinimumOfferPriceOfItem("1234");
        assertThat(minPrice.intValue(), equalTo(4 ));
    }


    @Test
    public void testFindAll() {
        List<Offer> emptyList = offerRepository.findAll();
        assertThat(emptyList, hasSize(0));

        Offer offer1 = new Offer();
        offer1.setUser(new Integer("3"));
        offer1.setQuantity(new Integer("3"));
        offer1.setPricePerUnit(new Integer("4"));
        offer1.setItemId("1234");
        Offer offerSaved1 = offerRepository.save(offer1);
        assertThat(offerSaved1.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(new Integer("4"));
        offer2.setQuantity(new Integer("2"));
        offer2.setPricePerUnit(new Integer("10"));
        offer2.setItemId("1234");
        Offer offerSaved2 = offerRepository.save(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        List<Offer> offerList = offerRepository.findAll();
        assertThat(offerList, hasSize(2));
        assertThat(offerList.contains(offerSaved2),is(true));

    }


    @Test
    public void testDeleteByItemId () {
        Integer sellerId = new Integer("3");
        Offer offer1 = new Offer();
        offer1.setUser(sellerId);
        offer1.setQuantity(new Integer("3"));
        offer1.setPricePerUnit(new Integer("4"));
        offer1.setItemId("1234");
        Offer offerSaved1 = offerRepository.save(offer1);
        assertThat(offerSaved1.getId(), is(notNullValue()));
        assertThat(offerSaved1.getUser(), is(sellerId.intValue()));
        Integer id = offerSaved1.getId();
        Offer offer = offerRepository.findById(id);
        assertThat(offer.getId(), is(id));
        offerRepository.deleteById(id);
        assertThat(offerRepository.findById(id), is(nullValue()));
    }

    @Test
    public void testFindByPricePerUnit() {
        Offer offer1 = new Offer();
        offer1.setUser(new Integer("3"));
        offer1.setQuantity(new Integer("3"));
        offer1.setPricePerUnit(new Integer("4"));
        offer1.setItemId("1234");
        Offer offerSaved = offerRepository.save(offer1);
        assertThat(offerSaved.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(new Integer("4"));
        offer2.setQuantity(new Integer("2"));
        offer2.setPricePerUnit(new Integer("10"));
        offer2.setItemId("1234");
        Offer offerSaved2 = offerRepository.save(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        Offer offer3 = new Offer();
        offer3.setUser(new Integer("4"));
        offer3.setQuantity(new Integer("3"));
        offer3.setPricePerUnit(new Integer("4"));
        offer3.setItemId("1234");
        Offer offerSaved3 = offerRepository.save(offer3);
        assertThat(offerSaved3.getId(), is(notNullValue()));

        List<Offer> offerList = offerRepository.findByPricePerUnit(new Integer("4"));
        assertThat(offerList, hasSize(2));

    }

    @Test
    public void testFindByItemId() {
        String itemId = "1234C";

        Offer offer1 = new Offer();
        offer1.setUser(new Integer("3"));
        offer1.setQuantity(new Integer("3"));
        offer1.setPricePerUnit(new Integer("4"));
        offer1.setItemId(itemId);
        Offer offerSaved = offerRepository.save(offer1);
        assertThat(offerSaved.getId(), is(notNullValue()));

        Offer offer2 = new Offer();
        offer2.setUser(new Integer("4"));
        offer2.setQuantity(new Integer("2"));
        offer2.setPricePerUnit(new Integer("10"));
        offer2.setItemId(itemId);
        Offer offerSaved2 = offerRepository.save(offer2);
        assertThat(offerSaved2.getId(), is(notNullValue()));

        Offer offer3 = new Offer();
        offer3.setUser(new Integer("4"));
        offer3.setQuantity(new Integer("3"));
        offer3.setPricePerUnit(new Integer("4"));
        offer3.setItemId(itemId);
        Offer offerSaved3 = offerRepository.save(offer3);
        assertThat(offerSaved3.getId(), is(notNullValue()));

        List<Offer> offerList = offerRepository.findByItemId(itemId);
        assertThat(offerList, hasSize(3));
        assertThat(offerList, contains(offer1, offer2, offer3));

    }
}
