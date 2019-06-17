package com.company.models.documents;

public class Outgoing extends Document {

    private String addressee;
    private String deliveryType;

    public Outgoing() {
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
