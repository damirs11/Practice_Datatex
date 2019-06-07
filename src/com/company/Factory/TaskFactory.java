package com.company.Factory;

import com.company.Models.Documents.Document;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;
import com.company.Utils.DataGeneratorUtils;

import java.util.Date;

public class TaskFactory extends DocumentFactory {

    public Document create() {
        Task doc = new Task(1, "", "", 1, new Date(), "");

        DataGeneratorUtils.generateRandomDataForDocument(doc);
        DataGeneratorUtils.generateRandomDataForTask(doc);

        return doc;
    }
}
