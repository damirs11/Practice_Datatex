package com.company.models.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement
public class Organization extends Staff {

    private String fullName;
    private String shortName;
    private Integer organizationHead;
    private Collection<String> phoneNumbers;

    public Organization() {
    }

    public Organization(int id) {
        super(id);
    }

    public Organization(int id, String fullName, String shortName, Integer organizationHead, Collection<String> phoneNumber) {
        super(id);
        this.fullName = fullName;
        this.shortName = shortName;
        this.organizationHead = organizationHead;
        this.phoneNumbers = phoneNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getOrganizationHead() {
        return organizationHead;
    }

    public Collection<String> getPhoneNumber() {
        return phoneNumbers;
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
    public void setOrganizationHead(Integer organizationHead) {
        this.organizationHead = organizationHead;
    }

    @XmlElementWrapper(name="phoneNumbers")
    @XmlElement(name="phoneNumber")
    public void setPhoneNumber(Collection<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", organizationHead='" + organizationHead + '\'' +
                ", phoneNumbers='" + phoneNumbers + '\'' +
                "} " + super.toString();
    }
}
