package com.company.Models.Documents;

import java.util.Date;

public class Task extends Document {

    private Date    dateRealize;
    private Date    periodOfExecution;
    private String  responsibleExecutor;
    private String  signOfControllability;
    private String  controller;

    public Task(int idDoc, String name, String text, int regId, Date regDate, String author) {
        super(idDoc, name, text, regId, regDate, author);
    }

    public Task(int idDoc, String name, String text, int regId, Date regDate, String author, Date dateRealize, Date periodOfExecution, String executiveOfficer, String signOfControllability, String controller) {
        super(idDoc, name, text, regId, regDate, author);
        this.dateRealize = dateRealize;
        this.periodOfExecution = periodOfExecution;
        this.responsibleExecutor = executiveOfficer;
        this.signOfControllability = signOfControllability;
        this.controller = controller;
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
        return "Task{" +
                "dateRealize=" + dateRealize +
                ", periodOfExecution=" + periodOfExecution +
                ", responsibleExecutor='" + responsibleExecutor + '\'' +
                ", signOfControllability='" + signOfControllability + '\'' +
                ", controller='" + controller + '\'' +
                "} " + super.toString();
    }
}
