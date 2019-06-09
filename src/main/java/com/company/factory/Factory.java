package com.company.factory;

import com.company.exception.DocumentExistsException;
import com.company.models.documents.Document;

public interface Factory {
    Document create() throws DocumentExistsException;

}
