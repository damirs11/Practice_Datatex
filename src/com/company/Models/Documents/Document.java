package com.company.Models.Documents;

import com.company.Interfaces.Storable;

import java.util.Date;

public abstract class Document implements Comparable<Document>, Storable {

    private int     id;
    private String  name;
    private String  text;
    private int     regId;
    private Date    regDate;
    private String  author;

    public Document(Document otherDoc){
        this.id = otherDoc.id;
        this.name = otherDoc.name;
        this.text = otherDoc.text;
        this.regId = otherDoc.regId;
        this.regDate = otherDoc.regDate;
        this.author = otherDoc.author;
    }

    public Document(int id, String name, String text, int regId, Date regDate, String author) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.regId = regId;
        this.regDate = regDate;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getRegId() {
        return regId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRegId(int regId) {
        this.regId = regId;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int compareTo(Document obj) {
        if(this.regId == obj.regId)
            if(this.regDate.equals(obj.regDate))
                return 0;
            else if(this.regDate.after(obj.regDate))
                return 1;
            else
                return -1;
        else if(this.regId < obj.regId)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "№" + id + " от " + regDate + ". " + name;
    }
}


