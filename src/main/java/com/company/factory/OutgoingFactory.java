package com.company.factory;

import com.company.models.documents.Outgoing;

class OutgoingFactory {
    static Outgoing create() {
        return new Outgoing();
    }
}
