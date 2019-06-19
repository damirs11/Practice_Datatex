package com.company.models.documents;

import com.company.annotation.RandomValue;
import com.company.enumeration.RandomTypes;

import java.util.Objects;

public class Outgoing extends Document {

    /**
     * Outgoing date of Outgoing Document
     */
    @RandomValue(RandomTypes.PERSON)
    private Integer addressee;
    /**
     * Type of DELIVERY
     */
    @RandomValue(RandomTypes.DELIVERY)
    private String deliveryType;

    public Integer getAddressee() {
        return addressee;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setAddressee(Integer addressee) {
        this.addressee = addressee;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        return "Исходящий " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Outgoing outgoing = (Outgoing) o;
        return Objects.equals(addressee, outgoing.addressee) &&
                Objects.equals(deliveryType, outgoing.deliveryType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), addressee, deliveryType);
    }
}
