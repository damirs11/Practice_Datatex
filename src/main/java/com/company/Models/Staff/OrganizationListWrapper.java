package com.company.Models.Staff;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;

@XmlRootElement(name = "Organizations")
public class OrganizationListWrapper {

    @XmlElement(name = "Organization")
    private ArrayList<Organization> organization;

    public ArrayList<Organization> getOrganization() {
        return organization;
    }

    @XmlTransient
    public void setOrganization(ArrayList<Organization> organization) {
        this.organization = organization;
    }
}

