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
import uk.co.marketplace.domain.Seller;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class SellerRepositoryTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Test
    public void testSaveSeller() {
        Seller seller = new Seller();
        seller.setName("Jack Jones");
        sellerRepository.save(seller);
        Seller retrievedSeller = sellerRepository.findById(seller.getId());
        assertNotNull(retrievedSeller);
        assertEquals(seller.getName(), retrievedSeller.getName());
    }
}
