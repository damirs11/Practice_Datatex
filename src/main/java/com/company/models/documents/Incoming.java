package com.company.models.documents;

import com.company.annotation.RandomValue;
import com.company.enumeration.RandomTypes;

import java.util.Date;
import java.util.Objects;

public class Incoming extends Document {

    /**
     * Sender of Incoming Document
     */
    @RandomValue(RandomTypes.PERSON_ID)
    private Integer sender;
    /**
     * Addressee of Incoming Document
     */
    @RandomValue(RandomTypes.PERSON_ID)
    private Integer addressee;
    /**
     * Outgoing number of Incoming Document
     */
    @RandomValue(RandomTypes.TEXT)
    private String outgoingNumber;
    /**
     * Outgoing date of Incoming Document
     */
    @RandomValue(RandomTypes.DATE)
    private Date outgoingDate;

    public Integer getSender() {
        return sender;
    }

    public Integer getAddressee() {
        return addressee;
    }

    public String getOutgoingNumber() {
        return outgoingNumber;
    }

    public Date getOutgoingDate() {
        return outgoingDate;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }

    public void setAddressee(Integer addressee) {
        this.addressee = addressee;
    }

    public void setOutgoingNumber(String outgoingNumber) {
        this.outgoingNumber = outgoingNumber;
    }

    public void setOutgoingDate(Date outgoingDate) {
        this.outgoingDate = outgoingDate;
    }


    @Override
    public String toString() {
        return "Входящий " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Incoming incoming = (Incoming) o;
        return Objects.equals(sender, incoming.sender) &&
                Objects.equals(addressee, incoming.addressee) &&
                Objects.equals(outgoingNumber, incoming.outgoingNumber) &&
                Objects.equals(outgoingDate, incoming.outgoingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), sender, addressee, outgoingNumber, outgoingDate);
    }
}
