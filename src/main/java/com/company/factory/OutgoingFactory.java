package com.company.factory;

import com.company.models.documents.Outgoing;

/**
 * The Outgoing factory.
 */
class OutgoingFactory {
    /**
     * Create Outgoing document.
     *
     * @return the Outgoing document.
     */
    static Outgoing create() {
        return new Outgoing();
    }
}
