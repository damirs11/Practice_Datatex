package com.company.factory;

import com.company.models.documents.Incoming;

class IncomingFactory {

    private IncomingFactory() {
        throw new IllegalStateException();
    }

    static Incoming create() {
        return new Incoming();
    }
}
