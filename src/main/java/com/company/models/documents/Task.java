package com.company.models.documents;

import com.company.annotation.RandomValue;
import com.company.enumeration.randomTypes;

import java.util.Date;
import java.util.Objects;

public class Task extends Document {

    @RandomValue(value = randomTypes.DATE)
    private Date dateRealize;
    @RandomValue(value = randomTypes.DATE)
    private Date periodOfExecution;
    @RandomValue(value = randomTypes.PERSON)
    private String responsibleExecutor;
    @RandomValue(value = randomTypes.TEXT)
    private String signOfControllability;
    @RandomValue(value = randomTypes.PERSON)
    private String controller;

    public Task() {
    }

    public Date getDateRealize() {
        return dateRealize;
    }

    public Date getPeriodOfExecution() {
        return periodOfExecution;
    }

    public String getResponsibleExecutor() {
        return responsibleExecutor;
    }

    public String getSignOfControllability() {
        return signOfControllability;
    }

    public String getController() {
        return controller;
    }

    public void setDateRealize(Date dateRealize) {
        this.dateRealize = dateRealize;
    }

    public void setPeriodOfExecution(Date periodOfExecution) {
        this.periodOfExecution = periodOfExecution;
    }

    public void setResponsibleExecutor(String executiveOfficer) {
        this.responsibleExecutor = executiveOfficer;
    }

    public void setSignOfControllability(String signOfControllability) {
        this.signOfControllability = signOfControllability;
    }

    public void setController(String controller) {
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
