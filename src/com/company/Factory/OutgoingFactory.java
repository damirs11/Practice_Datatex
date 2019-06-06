package com.company.Factory;

import com.company.Models.Documents.Document;
import com.company.Models.Documents.Outgoing;
import com.company.Utils.DataGeneratorUtils;

import java.util.Date;

public class OutgoingFactory extends DocumentFactory{

    public Document create() {
        Outgoing doc = new Outgoing(1, "", "", 1, new Date(), ""); //TODO Спросить, как можно это сделать лучше

        doc = (Outgoing) generateData(doc);

        return doc;
    }

    @Override
    public Document generateData(Document doc) {

        DataGeneratorUtils dataGeneratorUtils = new DataGeneratorUtils();

        Outgoing newDoc = (Outgoing) super.generateData(doc);

        newDoc.setAddressee(dataGeneratorUtils.takeRandomPerson());
        newDoc.setDeliveryType(dataGeneratorUtils.takeRandomDelivetyType());
        return newDoc;
    }
}
