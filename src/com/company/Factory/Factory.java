package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Documents.Document;

public interface Factory {
    Document create() throws DocumentExistsException;

}
