package com.company.models.staff;

import com.company.annotation.Column;
import com.company.annotation.Table;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Person class contain a object of some person
 * which have unique id form abstract class Staff
 */
@Table
@XmlRootElement(name = "Person")
public class Person extends Staff {

    /**
     * Second name of Person
     */
    @Column
    private String secondName;
    /**
     * Name of Person
     */
    @Column
    private String name;
    /**
     * Middle name of Person
     */
    @Column
    private String middleName;
    /**
     * Position of Person
     */
    @Column
    private String position;
    /**
     * Department id where Person work
     */
    @Column
    private Integer departmentId;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(secondName, person.secondName) &&
                Objects.equals(name, person.name) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(position, person.position) &&
                Objects.equals(departmentId, person.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), secondName, name, middleName, position, departmentId);
    }
}
