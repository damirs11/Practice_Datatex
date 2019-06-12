package com.company.models.documents;

import com.company.interfaces.Storable;

import java.util.Date;
import java.util.Objects;

public abstract class Document implements Comparable<Document>, Storable {

    private Integer id;
    private String name;
    private String text;
    private Integer regId;
    private Date regDate;
    private String author;

    public Document() {
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
        if (regDate.compareTo(obj.regDate) != 0) {
            return regDate.compareTo(obj.regDate);
        } else {
            return regId.compareTo(obj.regId);
        }
    }

    @Override
    public String toString() {
        return "№" + id + " от " + regDate + ". " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document document = (Document) o;
        return Objects.equals(id, document.id) &&
                Objects.equals(name, document.name) &&
                Objects.equals(text, document.text) &&
                Objects.equals(regId, document.regId) &&
                Objects.equals(regDate, document.regDate) &&
                Objects.equals(author, document.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, text, regId, regDate, author);
    }
}


