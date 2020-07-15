package com.company.model.staff;


import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * List wrapper create for (de)serialize objects
 * which is root element of <T> class
 *
 * @param <T> the type parameter
 */
@XmlRootElement(name = "elements")
public class ListWrapper<T> {

    @SerializedName("elements")
    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    @XmlAnyElement(lax = true)
    public void setList(List<T> list) {
        this.list = list;
    }
}
