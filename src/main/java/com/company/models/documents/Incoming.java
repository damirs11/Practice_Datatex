package com.company.models.documents;

import java.util.Date;

public class Incoming extends Document {

    private String sender;
    private String addressee;
    private String outgoingNumber;
    private Date outgoingDate;

    public Incoming(Integer id, String name, String text, Integer regId, Date regDate, String author) {
        super(id, name, text, regId, regDate, author);
    }

    public Incoming(Integer id, String name, String text, Integer regId, Date regDate, String author, String sender, String addressee, String outgoingNumber, Date outgoingDate) {
        super(id, name, text, regId, regDate, author);
        this.sender = sender;
        this.addressee = addressee;
        this.outgoingNumber = outgoingNumber;
        this.outgoingDate = outgoingDate;
    }

    public Incoming() {
        super();
    }

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
}
