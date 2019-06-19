package com.company.models.staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement
public class Department extends Staff {

    private String fullName;
    private String shortName;
    private Integer departmentHead;
    private Collection<String> phoneNumbers;
    private Integer organizationId;

    public Department() {
    }

    public Department(int id) {
        super(id);
    }

    public Department(int id, String fullName, String shortName, Integer departmentHead, Collection<String> phoneNumbers) {
        super(id);
        this.fullName = fullName;
        this.shortName = shortName;
        this.departmentHead = departmentHead;
        this.phoneNumbers = phoneNumbers;
    }

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
        return "Department{" +
                "fullName='" + fullName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", departmentHead='" + departmentHead + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                ", organizationId=" + organizationId +
                "} " + super.toString();
    }
}
