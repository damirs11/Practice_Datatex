package com.company.enumeration;

import com.company.models.documents.Document;
import com.company.models.documents.Incoming;
import com.company.models.documents.Outgoing;
import com.company.models.documents.Task;

public enum docTypes {
    OUTGOING (Outgoing.class),
    INCOMING (Incoming.class),
    TASK (Task.class);

    private final Class<? extends Document> type;

    docTypes(Class<? extends Document> aClass) {
        this.type = aClass;
    }

    public Class<? extends Document> getType() {
        return type;
    }
}
