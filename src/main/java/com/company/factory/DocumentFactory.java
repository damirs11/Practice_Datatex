package com.company.factory;

import com.company.enumeration.DocTypes;
import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;
import com.company.storage.IdDocumentsStorage;
import com.company.utils.DataGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * factory for production documents
 */
public abstract class DocumentFactory implements Factory {

    private static Logger logger = LoggerFactory.getLogger(DocumentFactory.class);

    /**
     * @param docType Document type
     * @return Document with generated data
     * @throws DocumentExistsException if idReg both documents are identical
     */
    public static Document create(DocTypes docType) {
        try {
            Document doc;

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

            //check doc for RandomValue annotation
            DataGeneratorUtils.generate(doc);
            //add Id to Store
            IdDocumentsStorage.add(doc);
            return doc;
        } catch (DocumentExistsException e) {
            logger.error("Error while try to create document " + e.getMessage());
        }
        return null;
    }
}
