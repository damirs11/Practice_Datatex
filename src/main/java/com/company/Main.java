package com.company;

import com.company.factory.DocumentFactory;
import com.company.models.documents.Document;
import com.company.models.staff.Person;
import com.company.parser.JaxbParser;
import com.company.utils.DataGeneratorUtils;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static final int NUMBER_DOCUMENT = 10;
    private static final String RESOURCE_PATH = Objects.requireNonNull(Main.class.getClassLoader().getResource("")).getPath();
    private static final String PERSON_INPUT_PATH = "InputXML/InputPerson.xml";
    private static final String OUTPUT_JSON_PATH = "OutputJson/";


    private static Gson gson = new Gson();
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            Collection<Person> persons = null;

            persons = JaxbParser.getObject(new File(RESOURCE_PATH, PERSON_INPUT_PATH), Person.class).getList();


            Collection<Document> documents = IntStream.range(0, NUMBER_DOCUMENT)
                    .mapToObj(value -> DocumentFactory.create(DataGeneratorUtils.getRandomDocType()))
                    .collect(Collectors.toList());

            for (Person person : persons) {

                Collection<Document> personDocuments = documents.stream()
                        .filter(document -> person.getId().equals(document.getAuthor()))
                        .collect(Collectors.toCollection(TreeSet::new));

                logger.info(person.toString());
                personDocuments.forEach(document -> logger.info(document.toString()));

                File filename = new File(String.format(RESOURCE_PATH + OUTPUT_JSON_PATH + "%s %s.json", person.getId(), person.getSecondName()));
                Writer writer = new FileWriter(filename);
                gson.toJson(personDocuments, writer);

            }
        } catch (IOException | JAXBException e) {
            logger.error(e.toString());
        } catch (Exception e) {
            logger.error(e.toString());
        }
    }

}
