package com.company;

import com.company.exception.DocumentExistsException;
import com.company.factory.DocumentFactory;
import com.company.models.documents.Document;
import com.company.utils.DataGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(Main.class);

        List<Document> documents = new ArrayList<>();

        //Создание документов
        for(int i = 0; i < 10; i++){
            try {
                documents.add(DocumentFactory.create(DataGeneratorUtils.getRandomDocType()));
            } catch (DocumentExistsException | IllegalAccessException e) {
                logger.error(e.toString());
            }
        }

        //Сортировка List в TreeMap
        Map<String, Collection<Document>> docsGrouped = documents.stream()
                .collect(Collectors.groupingBy(Document::getAuthor, Collectors.toCollection(ArrayList::new)));

        //Вывод
        docsGrouped.forEach( (s, docs) -> {
            logger.info(s);
            for (Document doc : docs) {
                logger.info(String.valueOf(doc));
            }
        });
    }

}
