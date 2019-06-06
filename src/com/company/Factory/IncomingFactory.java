package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Utils.DataGeneratorUtils;
import com.company.Utils.DateUtils;

import java.util.Date;

public class IncomingFactory extends DocumentFactory {

    private DateUtils dateUtils = new DateUtils();

    public Document create() {

        Incoming doc = new Incoming(1, "", "", 1, new Date(), ""); //TODO Спросить, как можно это сделать лучше

        doc = (Incoming) generateData(doc);

        return doc;
    }

    @Override
    public Document generateData(Document doc) {

        DataGeneratorUtils dataGeneratorUtils = new DataGeneratorUtils();

        Incoming newDoc = (Incoming) super.generateData(doc);

        newDoc.setAddressee(dataGeneratorUtils.takeRandomPerson());
        newDoc.setOutgoingNumber("Номер");
        newDoc.setSender(dataGeneratorUtils.takeRandomPerson());
        newDoc.setOutgoingDate(dateUtils.takeRandomDate(2000, 2020));

        return newDoc;
    }
}
