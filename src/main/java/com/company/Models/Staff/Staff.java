package com.company.Models.Staff;


public class Staff {
    private int id;

    public Staff(int id) {
        this.id = id;
    }

    public Staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                '}';
    }
}
