package com.company.Models.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person extends Staff {

    private String secondName;
    private String name;
    private String middleName;
    private String position;

    public Person() {
    }

    public Person(int id) {
        super(id);
    }

    public Person(int id, String secondName, String name, String middleName, String position) {
        super(id);
        this.secondName = secondName;
        this.name = name;
        this.middleName = middleName;
        this.position = position;
    }


    public String getSecondName() {
        return secondName;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPosition() {
        return position;
    }

    @XmlElement
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @XmlElement
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Person{" +
                "secondName='" + secondName + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                "} " + super.toString();
    }
}
