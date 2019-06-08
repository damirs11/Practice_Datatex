package com.company.Factory;

import com.company.Models.Documents.Outgoing;

public class OutgoingFactory extends DocumentFactory{

    public Outgoing create() {
        return new Outgoing();
    }
}
