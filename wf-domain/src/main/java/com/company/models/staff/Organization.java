package com.company.models.staff;

import com.company.annotation.Column;
import com.company.annotation.Table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Organization class contain a object of some organization
 * which have unique id form abstract class Staff
 */
@Table
@XmlRootElement
public class Organization extends Staff {

    /**
     * Full name of Organization
     */
    @Column
    private String fullName;
    /**
     * Short name of Organization
     */
    @Column
    private String shortName;
    /**
     * Organization head = id of Person
     */
    @Column
    private Integer organizationHead;
    /**
     * phone number
     */
    @Column
    private String phoneNumber;

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getOrganizationHead() {
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
    public void setOrganizationHead(Integer organizationHead) {
        this.organizationHead = organizationHead;
    }

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
