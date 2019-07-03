package com.company.models.staff;

import com.company.annotation.Column;
import com.company.annotation.Table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Department class contain a object of some department
 * which have unique id form abstract class Staff
 */
@Table
@XmlRootElement
public class Department extends Staff {

    /**
     * Full name of Department
     */
    @Column
    private String fullName;
    /**
     * Short name of Department
     */
    @Column
    private String shortName;
    /**
     * Department head = id of Person
     */
    @Column
    private Integer departmentHead;
    /**
     * Collection of phone numbers
     */
    @Column
    private String phoneNumber;
    /**
     * Organization id where Department contain
     */
    @Column
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

    public String getPhoneNumber() {
        return phoneNumber;
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

    @XmlElement
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement
    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        if (!super.equals(o)) return false;
        Department that = (Department) o;
        return Objects.equals(fullName, that.fullName) &&
                Objects.equals(shortName, that.shortName) &&
                Objects.equals(departmentHead, that.departmentHead) &&
                Objects.equals(phoneNumber, that.phoneNumber) &&
                Objects.equals(organizationId, that.organizationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fullName, shortName, departmentHead, phoneNumber, organizationId);
    }
}
