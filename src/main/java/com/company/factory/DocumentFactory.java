package com.company.factory;

import com.company.enumeration.docTypes;
import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;
import com.company.models.documents.Incoming;
import com.company.models.documents.Outgoing;
import com.company.models.documents.Task;
import com.company.storage.DocumentsStorage;
import com.company.utils.DataGeneratorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * factory for production documents
 */
public abstract class DocumentFactory implements Factory {

    private DocumentFactory() {
        throw new IllegalStateException();
    }

    /**
     * @param docType Document type
     * @return  Document with generated data
     * @throws DocumentExistsException if idReg both documents are identical
     */
    public static Document create(docTypes docType) throws DocumentExistsException {

        Document doc = null;

        if(Objects.equals(docType.getType(), Outgoing.class )) {
            doc = OutgoingFactory.create();
            DataGeneratorUtils.generateRandomDataForDocument( (Outgoing) doc);
        }

        if(Objects.equals(docType.getType(), Incoming.class )) {
            doc = IncomingFactory.create();
            DataGeneratorUtils.generateRandomDataForDocument( (Incoming) doc);
        }

        if(Objects.equals(docType.getType(), Task.class )) {
            doc = TaskFactory.create();
            DataGeneratorUtils.generateRandomDataForDocument( (Task) doc);
        }

        DocumentsStorage.add(doc);
        return doc;
    }
}
