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

@Entity
@Table(name="ITEMORDER")
public class ItemOrder {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="BUYERID", nullable = false)
    private Integer buyerId;

    @Column(name="SELLERID", nullable = false)
    private Integer sellerId;

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

    public Integer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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

        ItemOrder itemOrder = (ItemOrder) o;

        if (id != null ? !id.equals(itemOrder.id) : itemOrder.id != null) return false;
        if (buyerId != null ? !buyerId.equals(itemOrder.buyerId) : itemOrder.buyerId != null) return false;
        if (sellerId != null ? !sellerId.equals(itemOrder.sellerId) : itemOrder.sellerId != null) return false;
        if (itemId != null ? !itemId.equals(itemOrder.itemId) : itemOrder.itemId != null) return false;
        if (quantity != null ? !quantity.equals(itemOrder.quantity) : itemOrder.quantity != null) return false;
        return pricePerUnit != null ? pricePerUnit.equals(itemOrder.pricePerUnit) : itemOrder.pricePerUnit == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (buyerId != null ? buyerId.hashCode() : 0);
        result = 31 * result + (sellerId != null ? sellerId.hashCode() : 0);
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (pricePerUnit != null ? pricePerUnit.hashCode() : 0);
        return result;
    }

    public String toString() {
        return  " itemId:" + getItemId() +
                " quantity:" + getQuantity() +
                " pricePerUnit:" + getPricePerUnit() +
                " buyerId:"  + getBuyerId() +
                " sellerId:"  + getSellerId();
    }


}
