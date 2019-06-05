package com.company.Models.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Department extends Staff {

    private String fullName;
    private String shortName;
    private String departmentHead;
    private String phoneNumber;

    public Department() {
    }

    public Department(int id) {
        super(id);
    }

    public Department(int id, String fullName, String shortName, String departmentHead, String phoneNumber) {
        super(id);
        this.fullName = fullName;
        this.shortName = shortName;
        this.departmentHead = departmentHead;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDepartmentHead() {
        return departmentHead;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @XmlElement
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @XmlElement
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @XmlElement
    public void setDepartmentHead(String departmentHead) {
        this.departmentHead = departmentHead;
    }

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Department{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", departmentHead='" + departmentHead + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                "} " + super.toString();
    }
}
