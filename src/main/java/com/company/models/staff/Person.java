package com.company.models.staff;

import com.company.annotation.RandomValue;
import com.company.enumeration.RandomTypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Person class contain a object of some person
 * which have unique id form abstract class Staff
 */
@XmlRootElement(name = "Person")
public class Person extends Staff {

    /**
     * Second name of Person
     */
    @RandomValue(RandomTypes.PERSON_SECOND_NAME)
    private String secondName;
    /**
     * Name of Person
     */
    @RandomValue(RandomTypes.PERSON_NAME)
    private String name;
    /**
     * Middle name of Person
     */
    @RandomValue(RandomTypes.PERSON_MIDDLE_NAME)
    private String middleName;
    /**
     * Position of Person
     */
    @RandomValue(RandomTypes.TEXT)
    private String position;
    /**
     * Department id where Person work
     */
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
}
