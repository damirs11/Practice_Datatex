package com.company.Exception;

public class DocumentExistsException extends Exception {
    public DocumentExistsException() {
        super("Документ с таким регистрационным номером уже существует");
    }

    public DocumentExistsException(String errorMessage) {
        super(errorMessage);
    }
}
