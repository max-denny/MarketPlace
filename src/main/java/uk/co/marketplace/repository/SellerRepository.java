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
import org.springframework.data.repository.CrudRepository;
import uk.co.marketplace.domain.Seller;

/**
 * Represents a Seller
 */
public interface SellerRepository extends CrudRepository<Seller, Integer> {

    /**
     * Save a Seller
     * @param seller
     */
    Seller save(Seller seller);

    /**
     * Find a seller by there id
     * @param id
     * @return  seller represented by Id entered
     */
    Seller findById(Integer id);
}
