package com.company.storage;

import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;

import java.util.ArrayList;
import java.util.List;

public class DocumentsStorage {

    private DocumentsStorage() {
    }

    private static List<Document> documentList = new ArrayList<>();

    public static List<Document> getDocumentList() {
        return documentList;
    }

    public static void setDocumentList(List<Document> documentList) {
        DocumentsStorage.documentList = documentList;
    }

    public static void add(Document doc) throws DocumentExistsException {
        if(documentList.contains(doc)) {
            throw new DocumentExistsException(doc);
        } else {
            documentList.add(doc);
        }
    }
}