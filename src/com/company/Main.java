package com.company;


import com.company.Exception.DocumentExistsException;
import com.company.Factory.DocumentFactory;
import com.company.Models.*;

public class Main {

    public static void main(String[] args) throws DocumentExistsException {

        DocumentFactory factory = new DocumentFactory();

        for(int i = 0; i < 5; i++){
            factory.create(Outgoing.class);
            factory.create(Incoming.class);
            factory.create(Task.class);
        }


        factory.printAll();


    }

}
