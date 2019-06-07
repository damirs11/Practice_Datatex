package com.company.Models.Documents;

import java.util.Date;

public class Task extends Document {

    private Date    dateRealize;
    private Date    periodOfExecution;
    private String  responsibleExecutor;
    private String  signOfControllability;
    private String  controller;

    public Task(Document otherDoc) {
        super(otherDoc);
    }

    public Task(Document otherDoc, Date dateRealize, Date periodOfExecution, String responsibleExecutor, String signOfControllability, String controller) {
        super(otherDoc);
        this.dateRealize = dateRealize;
        this.periodOfExecution = periodOfExecution;
        this.responsibleExecutor = responsibleExecutor;
        this.signOfControllability = signOfControllability;
        this.controller = controller;
    }

    public Task(int id, String name, String text, int regId, Date regDate, String author) {
        super(id, name, text, regId, regDate, author);
    }

    public Task(int id, String name, String text, int regId, Date regDate, String author, Date dateRealize, Date periodOfExecution, String executiveOfficer, String signOfControllability, String controller) {
        super(id, name, text, regId, regDate, author);
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
        return "Поручение " + super.toString();
    }
}
