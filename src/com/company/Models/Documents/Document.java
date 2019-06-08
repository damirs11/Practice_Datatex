package com.company.Models.Documents;

import com.company.Interfaces.Storable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Document implements Comparable<Document>, Storable {

    private Integer     id;
    private String  name;
    private String  text;
    private Integer     regId;
    private Date    regDate;
    private String  author;

    public static final List<Document> allDocuments;
    static   {
        allDocuments = new ArrayList<>();
    }

    public Document() {
        allDocuments.add(this);
    }

    public Document(Integer id, String name, String text, Integer regId, Date regDate, String author) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.regId = regId;
        this.regDate = regDate;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public Integer getRegId() {
        return regId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRegId(Integer regId) {
        this.regId = regId;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int compareTo(Document obj) {
        if(regDate.compareTo(obj.regDate) != 0) {
            return regDate.compareTo(obj.regDate);
        } else {
            return  regId.compareTo(obj.regId);
        }
    }

    @Override
    public String toString() {
        return "№" + id + " от " + regDate + ". " + name;
    }

}


