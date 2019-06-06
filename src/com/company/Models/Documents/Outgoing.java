package com.company.Models.Documents;

import java.util.Date;

public class Outgoing extends Document {

    private String addressee;
    private String deliveryType;

    public Outgoing(int idDoc, String name, String text, int regId, Date regDate, String author) {
        super(idDoc, name, text, regId, regDate, author);
    }

    public Outgoing(int idDoc, String name, String text, int regId, Date regDate, String author, String addressee, String deliveryType) {
        super(idDoc, name, text, regId, regDate, author);
        this.addressee = addressee;
        this.deliveryType = deliveryType;
    }

    public String getAddressee() {
        return addressee;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    @Override
    public String toString() {
        return "Outgoing{" +
                "addressee='" + addressee + '\'' +
                ", deliveryType='" + deliveryType + '\'' +
                "} " + super.toString();
    }
}
