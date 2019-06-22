package com.company.utils;

public class SimplePerson {
    private String name;
    private String secondName;
    private String middleName;

    public SimplePerson(String name, String secondName, String middleName) {
        this.name = name;
        this.secondName = secondName;
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
}
