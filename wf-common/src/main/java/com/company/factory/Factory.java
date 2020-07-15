package com.company.factory;

import com.company.exception.DocumentExistsException;
import com.company.model.documents.Document;

/**
 * The interface Factory.
 */
public interface Factory {
    /**
     * Create document.
     *
     * @return the document
     * @throws DocumentExistsException the document exists exception
     */
    Document create() throws DocumentExistsException;

}
