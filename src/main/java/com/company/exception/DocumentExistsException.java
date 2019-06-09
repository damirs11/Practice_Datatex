package com.company.exception;

import com.company.models.documents.Document;

public class DocumentExistsException extends Exception {
    public DocumentExistsException(Document doc) {
        super("Документ с регистрационным номером " + doc.getRegId() + " уже существует");
    }

    public DocumentExistsException(String errorMessage) {
        super(errorMessage);
    }
}
