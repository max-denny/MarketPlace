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

/**
 * Class that represents an offer
 */
@Entity
@Table(name="OFFER")
public class Offer {

    @Id
    @GeneratedValue
    private int id;

    @Column(name="USER", nullable = false)
    private int user;

    @Column(name="ITEMID", nullable = false)
    private String itemId;

    @Column(name="QUANTITY", nullable = false)
    private int quantity;

    @Column(name="PRICEPERUNIT", nullable = false)
    private int pricePerUnit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Offer offer = (Offer) o;

        if (id != offer.id) return false;
        if (user != offer.user) return false;
        if (quantity != offer.quantity) return false;
        if (pricePerUnit != offer.pricePerUnit) return false;
        return itemId != null ? itemId.equals(offer.itemId) : offer.itemId == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + user;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + pricePerUnit;
        return result;
    }

    public String toString() {
        return  " itemId:" + getItemId() +
                " quantity:" + getQuantity() +
                " pricePerUnit:" + getPricePerUnit() +
                " user:" + getUser();
    }

}
