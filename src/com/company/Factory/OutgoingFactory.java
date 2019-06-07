package com.company.Factory;

import com.company.Models.Documents.Document;
import com.company.Models.Documents.Outgoing;
import com.company.Utils.DataGeneratorUtils;

import java.util.Date;

public class OutgoingFactory extends DocumentFactory{

    public Document create() {
        Outgoing doc = new Outgoing(1, "", "", 1, new Date(), "");

        DataGeneratorUtils.generateRandomDataForDocument(doc);
        DataGeneratorUtils.generateRandomDataForOutgoing(doc);

        return doc;
    }
}
