package com.company.models.staff;

public abstract class Staff {
    private Integer id;

    Staff(Integer id) {
        this.id = id;
    }

    Staff() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "staff{" +
                "id=" + id +
                '}';
    }
}
