package com.company;


import com.company.exception.DocumentExistsException;
import com.company.factory.DocumentFactory;
import com.company.models.documents.Document;
import com.company.models.staff.ListWrapper;
import com.company.models.staff.Person;
import com.company.parser.GsonParser;
import com.company.parser.JaxbParser;
import com.company.utils.DataGeneratorUtils;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws JAXBException, IOException, DocumentExistsException {

        File filePerson = new File(Objects.requireNonNull(Main.class.getClassLoader().getResource("InputXML/InputPerson.xml")).getFile());

        ListWrapper<Person> personListWrapper = JaxbParser.getObject(filePerson, Person.class);
        List<Person> personsList = personListWrapper.getList();

        int numberOfDocuments = 10;
        List<Document> documents = new ArrayList<>(numberOfDocuments);
        for(int i = 0; i < numberOfDocuments; i++){
            Document doc = DocumentFactory.create(DataGeneratorUtils.getRandomDocType());
            documents.add(doc);
        }

        File pathName = new File("src/main/resources/OutputJson/");
        pathName.mkdir();
        for(Person person: personsList) {
            File filename = new File(pathName.getAbsolutePath(), person.getId() + " " + person.getSecondName() + ".json");


            TreeSet<Document> personDocuments = new TreeSet<>();
            for(Document doc: documents) {
                if(person.getId().equals(doc.getAuthor())) {
                    personDocuments.add(doc);
                }
            }

            GsonParser.saveObject(filename, personDocuments);
        }


    }

}
