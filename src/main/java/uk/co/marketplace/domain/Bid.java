package uk.co.marketplace.domain;

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

import javax.persistence.*;
import java.io.Serializable;


/**
 * Class that represents the Bid
 */
@Entity
@Table(name="BID")
public class Bid implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="USER", nullable = false)
    private Integer user;

    @Column(name="ITEMID", nullable = false)
    private String itemId;

    @Column(name="QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name="PRICEPERUNIT", nullable = false)
    private Integer pricePerUnit;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Integer pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bid bid = (Bid) o;

        if (id != null ? !id.equals(bid.id) : bid.id != null) return false;
        if (user != null ? !user.equals(bid.user) : bid.user != null) return false;
        if (itemId != null ? !itemId.equals(bid.itemId) : bid.itemId != null) return false;
        if (quantity != null ? !quantity.equals(bid.quantity) : bid.quantity != null) return false;
        return pricePerUnit != null ? pricePerUnit.equals(bid.pricePerUnit) : bid.pricePerUnit == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (pricePerUnit != null ? pricePerUnit.hashCode() : 0);
        return result;
    }

    public String toString() {
        return  " itemId:" + getItemId() +
                " quantity:" + getQuantity() +
                " pricePerUnit:" + getPricePerUnit() +
                " user:" + getUser();
    }


}
