package com.company.factory;

import com.company.models.documents.Incoming;

class IncomingFactory {
    static Incoming create() {
        return new Incoming();
    }
}
