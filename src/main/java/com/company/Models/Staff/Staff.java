package com.company.Models.Staff;


public abstract class Staff {
    private int id;

    Staff(int id) {
        this.id = id;
    }

    Staff() {
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
