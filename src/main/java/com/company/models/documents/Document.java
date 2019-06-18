package com.company.models.documents;

import com.company.annotation.RandomValue;
import com.company.enumeration.randomTypes;
import com.company.interfaces.Storable;

import java.util.Date;
import java.util.Objects;

public abstract class Document implements Comparable<Document>, Storable {

    @RandomValue(value = randomTypes.INTEGER)
    private Integer id;
    @RandomValue(value = randomTypes.TEXT)
    private String name;
    @RandomValue(value = randomTypes.TEXT)
    private String text;
    @RandomValue(value = randomTypes.INTEGER)
    private Integer regId;
    @RandomValue(value = randomTypes.DATE)
    private Date regDate;
    @RandomValue(value = randomTypes.PERSON)
    private String author;

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


