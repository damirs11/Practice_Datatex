package com.company.enumeration;

/**
 * The enum Delivery type.
 */
public enum DeliveryType {
    TYPE1 ("Способ доставки 1"),
    TYPE2 ("Способ доставки 2"),
    TYPE3 ("Способ доставки 3"),
    TYPE4 ("Способ доставки 4");

    private final String type;

    DeliveryType(String s) {
        this.type = s;
    }

    public String getType() {
        return type;
    }
}
