package com.company.model.documents;

import com.company.annotation.RandomValue;
import com.company.enumeration.RandomTypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement
public class Outgoing extends Document {

    /**
     * Outgoing date of Outgoing Document
     */
    @RandomValue(RandomTypes.PERSON_ID)
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

    @XmlElement
    public void setAddressee(Integer addressee) {
        this.addressee = addressee;
    }

    @XmlElement
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
