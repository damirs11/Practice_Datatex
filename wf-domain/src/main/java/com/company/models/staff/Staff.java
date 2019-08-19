package com.company.models.staff;

import com.company.annotation.Column;
import com.company.annotation.Id;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

/**
 * Abstract class which contain an id
 * of children
 */
public abstract class Staff implements Comparable<Staff> {
    /**
     * Id of Staff
     */
    @Id
    @Column
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE,
                true, true);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return id.equals(staff.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Staff staff) {
        if (id.compareTo(staff.id) != 0)
            return id.compareTo(staff.id);
        return 0;
    }
}
