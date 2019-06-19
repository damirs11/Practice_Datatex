package com.company.models.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Person")
public class Person extends Staff {

    private String secondName;
    private String name;
    private String middleName;
    private String position;

    private Integer departmentId;


    public Person() {
    }

    public Person(int id) {
        super(id);
    }

    public Person(int id, String secondName, String name, String middleName, String position, Integer departmentId) {
        super(id);
        this.secondName = secondName;
        this.name = name;
        this.middleName = middleName;
        this.position = position;
        this.departmentId = departmentId;
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

    public Integer getDepartmentId() {
        return departmentId;
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

    @XmlElement
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "secondName='" + secondName + '\'' +
                ", name='" + name + '\'' +
                ", middleName='" + middleName + '\'' +
                ", position='" + position + '\'' +
                ", departmentId=" + departmentId +
                "} " + super.toString();
    }
}
