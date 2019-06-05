package com.company.Models.Documents;

import com.company.Interfaces.Storable;

import java.util.Date;

public abstract class Document implements Comparable<Document>, Storable {

    private int     idDoc;
    private String  name;
    private String  text;
    private int     reg_idDoc;
    private Date    regDate;
    private String  author;

    public Document() {
    }

    public Document(int idDoc, String name, String text, int reg_idDoc, Date regDate, String author) {
        this.idDoc = idDoc;
        this.name = name;
        this.text = text;
        this.reg_idDoc = reg_idDoc;
        this.regDate = regDate;
        this.author = author;
    }

    public int getIdDoc() {
        return idDoc;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public int getReg_idDoc() {
        return reg_idDoc;
    }

    public Date getRegDate() {
        return regDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setIdDoc(int idDoc) {
        this.idDoc = idDoc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setReg_idDoc(int reg_idDoc) {
        this.reg_idDoc = reg_idDoc;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public int compareTo(Document obj) {
        if(this.reg_idDoc == obj.reg_idDoc)
            if(this.regDate.equals(obj.regDate))
                return 0;
            else if(this.regDate.after(obj.regDate))
                return 1;
            else
                return -1;
        else if(this.reg_idDoc < obj.reg_idDoc)
            return 1;
        else
            return -1;
    }

    @Override
    public String toString() {
        return "Document{" +
                "idDoc=" + idDoc +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", reg_idDoc=" + reg_idDoc +
                ", regDate=" + regDate +
                ", author='" + author + '\'' +
                '}';
    }
}


