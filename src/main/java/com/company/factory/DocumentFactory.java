package com.company.factory;

import com.company.enumeration.docTypes;
import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;
import com.company.models.documents.Incoming;
import com.company.models.documents.Outgoing;
import com.company.models.documents.Task;
import com.company.utils.DataGeneratorUtils;

import java.util.Objects;


/**
 * factory for production documents
 */
public abstract class DocumentFactory implements Factory {


    /**
     * @param docType Document type
     * @return  Document with generated data
     * @throws DocumentExistsException if idReg both documents are identical
     */
    public static Document create(docTypes docType) throws DocumentExistsException {

        Document doc = null;

        if(Objects.equals(docType.getType(), Outgoing.class )) {
            doc = new OutgoingFactory().create();
            DataGeneratorUtils.generateRandomDataForDocument( (Outgoing) doc);
        }

        if(Objects.equals(docType.getType(), Incoming.class )) {
            doc = new IncomingFactory().create();
            DataGeneratorUtils.generateRandomDataForDocument( (Incoming) doc);
        }

        if(Objects.equals(docType.getType(), Task.class )) {
            doc = new TaskFactory().create();
            DataGeneratorUtils.generateRandomDataForDocument( (Task) doc);
        }
        
        if(doc == null) {
            throw new IllegalArgumentException();
        }
        
        checkId(doc);
        return doc;
    }

    private static void checkId(Document doc) throws DocumentExistsException {
        int count = 0;
        for(Document document: Document.allDocuments) {
            if(document.getRegId().equals(doc.getRegId()) && (++count > 2)){
                throw new DocumentExistsException(doc);
            }
        }
    }

}
