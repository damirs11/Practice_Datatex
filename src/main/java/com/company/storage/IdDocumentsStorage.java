package com.company.storage;

import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Store all Ids of Documents
 */
public class IdDocumentsStorage {

    private static Collection<Integer> idDocumentsList = new ArrayList<>();

    /**
     *
     * @return the id documents list
     */
    public static Collection<Integer> getIdDocumentsList() {
        return idDocumentsList;
    }

    /**
     * Add Document id with DocumentExistsException check
     *
     * @param doc the Document
     * @throws DocumentExistsException the document exists exception
     */
    public static void add(Document doc) throws DocumentExistsException {
        if(idDocumentsList.contains(doc.getId())) {
            throw new DocumentExistsException(doc);
        } else {
            idDocumentsList.add(doc.getId());
        }
    }
}