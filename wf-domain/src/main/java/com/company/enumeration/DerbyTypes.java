package com.company.enumeration;

public enum DerbyTypes {
    CLOB("CLOB"),
    BLOB("BLOB"),
    LONG_VARCHAR("LONG VARCHAR"),
    AUTO("AUTO");

    private final String type;

    DerbyTypes(String s) {
        this.type = s;
    }

    public String getType() {
        return type;
    }
}
