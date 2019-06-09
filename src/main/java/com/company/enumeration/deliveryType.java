package com.company.enumeration;

public enum deliveryType {
    TYPE1 ("Способ доставки 1"),
    TYPE2 ("Способ доставки 2"),
    TYPE3 ("Способ доставки 3"),
    TYPE4 ("Способ доставки 4");

    private final String type;

    deliveryType(String s) {
        this.type = s;
    }

    public String getType() {
        return type;
    }
}
