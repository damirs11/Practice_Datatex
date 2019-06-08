package com.company.Factory;

import com.company.Models.Documents.Incoming;

public class IncomingFactory extends DocumentFactory {

    public Incoming create() {
        return new Incoming();
    }
}
