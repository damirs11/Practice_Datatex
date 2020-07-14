package com.company.model.staff;

import com.company.annotation.Column;
import com.company.annotation.Table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
}
