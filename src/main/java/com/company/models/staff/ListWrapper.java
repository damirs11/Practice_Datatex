package com.company.models.staff;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;


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
