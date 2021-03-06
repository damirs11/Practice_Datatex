package com.company.factory;

import com.company.models.documents.Incoming;

/**
 * The Incoming factory.
 */
class IncomingFactory {
    /**
     * Create Incoming document.
     *
     * @return the Incoming document
     */
    static Incoming create() {
        return new Incoming();
    }
}
