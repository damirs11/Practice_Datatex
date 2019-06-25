package com.company.services;

/**
 * The enum of Tables.
 */
public enum Tables {
    PERSONS("Persons");

    private String table;

    Tables(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}
