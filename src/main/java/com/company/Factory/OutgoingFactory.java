package com.company.factory;

import com.company.models.documents.Outgoing;

class OutgoingFactory {

    private OutgoingFactory() {
        throw new IllegalStateException();
    }

    static Outgoing create() {
        return new Outgoing();
    }
}
