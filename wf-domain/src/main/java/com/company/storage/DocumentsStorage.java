package com.company.storage;

import com.company.models.documents.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Store all existing Documents
 */
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
}
