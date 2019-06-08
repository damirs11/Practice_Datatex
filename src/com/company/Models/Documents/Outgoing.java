package com.company.Models.Documents;

import java.util.Date;

public class Outgoing extends Document {

    private String addressee;
    private String deliveryType;

    public Outgoing(int id, String name, String text, int regId, Date regDate, String author) {
        super(id, name, text, regId, regDate, author);
    }

    public Outgoing(int id, String name, String text, int regId, Date regDate, String author, String addressee, String deliveryType) {
        super(id, name, text, regId, regDate, author);
        this.addressee = addressee;
        this.deliveryType = deliveryType;
    }

    public Outgoing() {
        super();
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
        return "Исходящий " + super.toString();
    }
}
