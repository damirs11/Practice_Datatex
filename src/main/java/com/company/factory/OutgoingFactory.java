package com.company.factory;

import com.company.models.documents.Outgoing;

public class OutgoingFactory extends DocumentFactory{

    public Outgoing create() {
        return new Outgoing();
    }
}
