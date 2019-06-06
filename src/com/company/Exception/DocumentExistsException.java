package com.company.Exception;

import com.company.Models.Documents.Document;

public class DocumentExistsException extends Exception {
    public DocumentExistsException(Document doc) {
        super("Документ с регистрационным номером " + doc.getRegId() + " уже существует");
    }

    public DocumentExistsException(String errorMessage) {
        super(errorMessage);
    }
}
