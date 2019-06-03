package com.company;


import com.company.Factory.DocumentFactory;
import com.company.Factory.OutgoingFactory;
import com.company.Objects.Document;

public class Main {

    public static void main(String[] args){

        DocumentFactory facD = new OutgoingFactory();
        Document doc = facD.create();

        System.out.print(doc.toString());
    }

}
