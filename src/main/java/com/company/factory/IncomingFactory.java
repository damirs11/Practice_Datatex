package com.company.factory;

import com.company.models.documents.Incoming;

public class IncomingFactory extends DocumentFactory {

    public Incoming create() {
        return new Incoming();
    }
}
