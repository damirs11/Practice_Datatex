package com.company.Factory;

import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Utils.DataGeneratorUtils;

import java.util.Date;

public class IncomingFactory extends DocumentFactory {

    public Document create() {
        Incoming doc = new Incoming(1, "", "", 1, new Date(), "");

        DataGeneratorUtils.generateRandomDataForDocument(doc);
        DataGeneratorUtils.generateRandomDataForIncoming(doc);

        return doc;
    }
}
