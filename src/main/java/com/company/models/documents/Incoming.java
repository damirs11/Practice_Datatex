package com.company.models.documents;

import com.company.annotation.RandomValue;
import com.company.enumeration.randomTypes;

import java.util.Date;
import java.util.Objects;

public class Incoming extends Document {

    @RandomValue(value = randomTypes.PERSON)
    private String sender;
    @RandomValue(value = randomTypes.PERSON)
    private String addressee;
    @RandomValue(value = randomTypes.TEXT)
    private String outgoingNumber;
    @RandomValue(value = randomTypes.DATE)
    private Date outgoingDate;

    public String getSender() {
        return sender;
    }

    public String getAddressee() {
        return addressee;
    }

    public String getOutgoingNumber() {
        return outgoingNumber;
    }

    public Date getOutgoingDate() {
        return outgoingDate;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setAddressee(String addressee) {
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
