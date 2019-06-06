package com.company.Models.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

@XmlRootElement(name = "Departments")
public class DepartmentListWrapper {

    @XmlElement(name = "Department")
    private ArrayList<Department> department;

    public ArrayList<Department> getDepartment() {
        return department;
    }

    @XmlTransient
    public void setDepartment(ArrayList<Department> department) {
        this.department = department;
    }
}
