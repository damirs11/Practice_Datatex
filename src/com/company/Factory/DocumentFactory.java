package com.company.Factory;

import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;

import java.util.Objects;


public abstract class DocumentFactory implements Factory {

    public static Document create(Class<? extends Document> docType) {

        if(Objects.equals(docType.getName(), Outgoing.class.getName() )) {
            return new OutgoingFactory().create();
        }

        if(Objects.equals(docType.getName(), Incoming.class.getName() )) {
            return new IncomingFactory().create();
        }

        if(Objects.equals(docType.getName(), Task.class.getName() )) {
            return new TaskFactory().create();
        }

        throw new IllegalArgumentException();
    }
}
