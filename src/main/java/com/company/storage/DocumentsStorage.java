package com.company.storage;

import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;

import java.util.ArrayList;
import java.util.Collection;

public class DocumentsStorage {

    private DocumentsStorage() {
    }

    private static Collection<Integer> documentList = new ArrayList<>();

    public static Collection<Integer> getDocumentList() {
        return documentList;
    }

    public static void add(Document doc) throws DocumentExistsException {
        if(documentList.contains(doc.getId())) {
            throw new DocumentExistsException(doc);
        } else {
            documentList.add(doc.getId());
        }
    }
}