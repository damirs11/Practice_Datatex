package com.company.Objects;

import java.util.Date;

public class Incoming extends Document {

    private String  sender;
    private String  addressee;
    private String  outgoingNumber;
    private Date    outgoingDate;

    public Incoming(int idDoc, String name, String text, int reg_idDoc, Date regDate, String author) {
        super(idDoc, name, text, reg_idDoc, regDate, author);
    }

    public Incoming(int idDoc, String name, String text, int reg_idDoc, Date regDate, String author, String sender, String addressee, String outgoingNumber, Date outgoingDate) {
        super(idDoc, name, text, reg_idDoc, regDate, author);
        this.sender = sender;
        this.addressee = addressee;
        this.outgoingNumber = outgoingNumber;
        this.outgoingDate = outgoingDate;
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
        return "Incoming{" +
                "sender='" + sender + '\'' +
                ", addressee='" + addressee + '\'' +
                ", outgoingNumber='" + outgoingNumber + '\'' +
                ", outgoingDate=" + outgoingDate +
                "} " + super.toString();
    }
}
