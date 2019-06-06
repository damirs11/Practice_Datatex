package com.company.Models.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

@XmlRootElement(name = "Persons")
public class PersonListWrapper {

    // XmLElementWrapper generates a wrapper element around XML representation
    @XmlElement(name = "Person")
    private ArrayList<Person> persons;

    public ArrayList<Person> getPersonList() {
        return persons;
    }

    @XmlTransient
    public void setPersonList(ArrayList<Person> personList) {
        this.persons = personList;
    }
}
