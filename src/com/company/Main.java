package com.company;


import com.company.Exception.DocumentExistsException;
import com.company.Factory.DocumentFactory;
import com.company.Models.Documents.Document;
import com.company.Utils.DataGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws DocumentExistsException {

        Logger logger = LoggerFactory.getLogger(Main.class);

        List<Document> documents = new ArrayList<>();

        for(Integer i = 0; i < 10; i++){
            Document doc = DocumentFactory.create(DataGeneratorUtils.getRandomDocType());
            documents.add(doc);
        }

        //Сортировка List в TreeMap
        TreeMap<String, List<Document>> docsGrouped = documents.stream()
                .collect(Collectors.groupingBy(Document::getAuthor, TreeMap::new, Collectors.toList()));


        docsGrouped.forEach( (s, docs) -> {
            logger.info(s);
            for (Document doc : docs) {
                logger.info(String.valueOf(doc));
            }
        });
    }

}
