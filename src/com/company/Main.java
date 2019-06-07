package com.company;


import com.company.Exception.DocumentExistsException;
import com.company.Factory.DocumentFactory;
import com.company.Models.Documents.Document;
import com.company.Utils.DataGeneratorUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {


    //TODO подключить логгер
    //TODO Реализовать группировку по авторам
    public static void main(String[] args) throws DocumentExistsException {

        List<Document> documents = new ArrayList<>();

        for(int i = 0; i < 10; i++){
            Document doc = DocumentFactory.create(DataGeneratorUtils.getRandomDocType());
            documents.add(doc);
        }

    }

}
