package com.company.Models.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Organization extends Staff {

    private String fullName;
    private String shortName;
    private String organizationHead;
    private String phoneNumber;

    public Organization() {
    }

    public Organization(int id) {
        super(id);
    }

    public Organization(int id, String fullName, String shortName, String organizationHead, String phoneNumber) {
        super(id);
        this.fullName = fullName;
        this.shortName = shortName;
        this.organizationHead = organizationHead;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getOrganizationHead() {
        return organizationHead;
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
    public void setOrganizationHead(String organizationHead) {
        this.organizationHead = organizationHead;
    }

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", organizationHead='" + organizationHead + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                "} " + super.toString();
    }
}