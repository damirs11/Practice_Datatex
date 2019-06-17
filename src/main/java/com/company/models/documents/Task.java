package com.company.models.documents;

import java.util.Date;

public class Task extends Document {

    private Date dateRealize;
    private Date periodOfExecution;
    private String responsibleExecutor;
    private String signOfControllability;
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
}
