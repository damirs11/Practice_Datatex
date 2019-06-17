package com.company.factory;

import com.company.enumeration.docTypes;
import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;
import com.company.storage.DocumentsStorage;
import com.company.utils.DataGeneratorUtils;


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

        switch (docType) {
            case OUTGOING:
                doc = OutgoingFactory.create();
                break;
            case INCOMING:
                doc = IncomingFactory.create();
                break;
            case TASK:
                doc = TaskFactory.create();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + docType);
        }

        DataGeneratorUtils.generateRandomDataForDocument(doc); //TODO переделать генерацию
        DocumentsStorage.add(doc);
        return doc;
    }
}
