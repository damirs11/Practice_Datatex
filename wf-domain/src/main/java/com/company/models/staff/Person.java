package com.company.models.staff;

import com.company.annotation.Column;
import com.company.annotation.Table;
import com.company.enumeration.DerbyTypes;

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
    private String surname;
    /**
     * Name of Person
     */
    @Column
    private String firstname;
    /**
     * Middle name of Person
     */
    @Column
    private String patronymic;
    /**
     * Position of Person
     */
    @Column
    private String post;
    /**
     * Department id where Person work
     */
    @Column
    private Integer departmentId;
    /**
     * Store image in Base64 format
     */
    @Column(dataType = DerbyTypes.LONG_VARCHAR)
    private String photo;

    public String getSurname() {
        return surname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getPost() {
        return post;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getPhoto() {
        return photo;
    }

    @XmlElement
    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlElement
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @XmlElement
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @XmlElement
    public void setPost(String post) {
        this.post = post;
    }

    @XmlElement
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @XmlElement
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        if (!super.equals(o)) return false;
        Person person = (Person) o;
        return Objects.equals(surname, person.surname) &&
                Objects.equals(firstname, person.firstname) &&
                Objects.equals(patronymic, person.patronymic) &&
                Objects.equals(post, person.post) &&
                Objects.equals(departmentId, person.departmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), surname, firstname, patronymic, post, departmentId);
    }
}
