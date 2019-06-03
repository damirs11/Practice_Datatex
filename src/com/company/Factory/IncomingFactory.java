package com.company.Factory;

import com.company.Objects.Document;
import com.company.Objects.Incoming;

import java.util.Date;

public class IncomingFactory extends DocumentFactory {


    public Document create(){
        return new Incoming(1, "string", "string", 1, new Date(), "author");
    }
}
