package com.company.models.staff;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

/**
 * Department class contain a object of some department
 * which have unique id form abstract class Staff
 */
@XmlRootElement
public class Department extends Staff {

    /**
     * Full name of Department
     */
    private String fullName;
    /**
     * Short name of Department
     */
    private String shortName;
    /**
     * Department head = id of Person
     */
    private Integer departmentHead;
    /**
     * Collection of phone numbers
     */
    private Collection<String> phoneNumbers;
    /**
     * Organization id where Department contain
     */
    private Integer organizationId;

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public Integer getDepartmentHead() {
        return departmentHead;
    }

    public Collection<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public Integer getOrganizationId() {
        return organizationId;
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
    public void setDepartmentHead(Integer departmentHead) {
        this.departmentHead = departmentHead;
    }

    @XmlElementWrapper(name="phoneNumbers")
    @XmlElement(name="phoneNumber")
    public void setPhoneNumbers(Collection<String> phoneNumber) {
        this.phoneNumbers = phoneNumber;
    }

    @XmlElement
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE,
                true, true);
    }
}
