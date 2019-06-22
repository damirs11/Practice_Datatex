package com.company.models.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * Organization class contain a object of some organization
 * which have unique id form abstract class Staff
 */
@XmlRootElement
public class Organization extends Staff {

    /**
     * Full name of Organization
     */
    private String fullName;
    /**
     * Short name of Organization
     */
    private String shortName;
    /**
     * Organization head = id of Person
     */
    private Integer organizationHead;
    /**
     * Collection of phone numbers
     */
    private Collection<String> phoneNumbers;

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

    @XmlElementWrapper(name = "phoneNumbers")
    @XmlElement(name = "phoneNumber")
    public void setPhoneNumber(Collection<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
