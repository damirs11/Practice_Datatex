package com.company.models.staff;

import com.company.annotation.RandomValue;
import com.company.enumeration.RandomTypes;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Abstract class which contain an id
 * of children
 */
public abstract class Staff {
    /**
     * Id of Staff
     */
    @RandomValue(RandomTypes.UNIQUE_ID)
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
}
