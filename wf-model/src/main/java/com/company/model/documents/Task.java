package com.company.model.documents;

import com.company.annotation.RandomValue;
import com.company.enumeration.RandomTypes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
public class Task extends Document {

    /**
     * Date of realize
     */
    @RandomValue(RandomTypes.DATE)
    private Date dateRealize;
    /**
     * Period of execution
     */
    @RandomValue(RandomTypes.DATE)
    private Date periodOfExecution;
    /**
     * Responsible executor
     */
    @RandomValue(RandomTypes.PERSON_ID)
    private Integer responsibleExecutor;
    /**
     * Sign of controllability
     */
    @RandomValue(RandomTypes.TEXT)
    private String signOfControllability;
    /**
     * Controller of Task
     */
    @RandomValue(RandomTypes.PERSON_ID)
    private Integer controller;

    public Date getDateRealize() {
        return dateRealize;
    }

    public Date getPeriodOfExecution() {
        return periodOfExecution;
    }

    public Integer getResponsibleExecutor() {
        return responsibleExecutor;
    }

    public String getSignOfControllability() {
        return signOfControllability;
    }

    public Integer getController() {
        return controller;
    }

    @XmlElement
    public void setDateRealize(Date dateRealize) {
        this.dateRealize = dateRealize;
    }

    @XmlElement
    public void setPeriodOfExecution(Date periodOfExecution) {
        this.periodOfExecution = periodOfExecution;
    }

    @XmlElement
    public void setResponsibleExecutor(Integer executiveOfficer) {
        this.responsibleExecutor = executiveOfficer;
    }

    @XmlElement
    public void setSignOfControllability(String signOfControllability) {
        this.signOfControllability = signOfControllability;
    }

    @XmlElement
    public void setController(Integer controller) {
        this.controller = controller;
    }

    @Override
    public String toString() {
        return "Поручение " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Task task = (Task) o;
        return Objects.equals(dateRealize, task.dateRealize) &&
                Objects.equals(periodOfExecution, task.periodOfExecution) &&
                Objects.equals(responsibleExecutor, task.responsibleExecutor) &&
                Objects.equals(signOfControllability, task.signOfControllability) &&
                Objects.equals(controller, task.controller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateRealize, periodOfExecution, responsibleExecutor, signOfControllability, controller);
    }
}
