package com.company.Models.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


@XmlRootElement(name = "Persons")
public class PersonsList {


    private List<Person> personList = new ArrayList<>();

    public List<Person> getPersonList() {
        return personList;
    }

    @XmlElement(name = "Person")
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
