package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;
import com.company.Utils.DataGeneratorUtils;

import java.util.Objects;


/**
 * Factory for production Documents
 */
public abstract class DocumentFactory implements Factory {


    /**
     * @param docType Document type
     * @return  Document with generated data
     * @throws DocumentExistsException if idReg both documents are identical
     */
    public static Document create(Class<? extends Document> docType) throws DocumentExistsException {

        if(Objects.equals(docType.getName(), Outgoing.class.getName() )) {
            Outgoing doc = new OutgoingFactory().create();
            DataGeneratorUtils.generateRandomDataForDocument(doc);

            checkId(doc);

            return doc;
        }

        if(Objects.equals(docType.getName(), Incoming.class.getName() )) {
            Incoming doc = new IncomingFactory().create();
            DataGeneratorUtils.generateRandomDataForDocument(doc);

            checkId(doc);

            return doc;
        }

        if(Objects.equals(docType.getName(), Task.class.getName() )) {
            Task doc = new TaskFactory().create();
            DataGeneratorUtils.generateRandomDataForDocument(doc);

            checkId(doc);

            return doc;
        }

        throw new IllegalArgumentException();
    }

    private static void checkId(Document doc) throws DocumentExistsException {
        int count = 0;
        for(Document document: Document.allDocuments) {
            if(document.getRegId().equals(doc.getRegId()) && (count == 2)){
                throw new DocumentExistsException(doc);
            }
        }
    }

}
