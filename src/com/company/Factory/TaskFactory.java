package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Documents.Document;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;
import com.company.Utils.DataGeneratorUtils;
import com.company.Utils.DateUtils;

import java.util.Date;

public class TaskFactory extends DocumentFactory {

    private DateUtils dateUtils = new DateUtils();

    public Document create() {
        Task doc = new Task(1, "", "", 1, new Date(), ""); //TODO Спросить, как можно это сделать лучше

        doc = (Task) generateData(doc);

        return doc;
    }

    @Override
    public Document generateData(Document doc) {

        DataGeneratorUtils dataGeneratorUtils = new DataGeneratorUtils();

        Task newDoc = (Task) super.generateData(doc);

        newDoc.setController(dataGeneratorUtils.takeRandomPerson());
        newDoc.setDateRealize(dateUtils.takeRandomDate(2000, 2020));
        newDoc.setResponsibleExecutor(dataGeneratorUtils.takeRandomPerson());
        newDoc.setPeriodOfExecution(dateUtils.addDays(newDoc.getDateRealize(), 30));
        newDoc.setSignOfControllability("Признак контрольности");

        return newDoc;
    }

}
