package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;
import com.company.Utils.DataGeneratorUtils;
import com.company.Utils.DateUtils;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public abstract class  DocumentFactory implements Factory {

    private Random random = new Random();

    public static Document create(Class<? extends Document> docType) {

        if(Objects.equals(docType.getName(), Outgoing.class.getName() )) {
            return new OutgoingFactory().create();
        }

        if(Objects.equals(docType.getName(), Incoming.class.getName() )) {
            return new IncomingFactory().create();
        }

        if(Objects.equals(docType.getName(), Task.class.getName() )) {
            return new TaskFactory().create();
        }

        throw new IllegalArgumentException();
    }

    public Document generateData(Document doc) {

        DateUtils dateUtils = new DateUtils();
        DataGeneratorUtils dataGeneratorUtils = new DataGeneratorUtils();

        doc.setIdDoc(random.nextInt(1000));
        doc.setName("Название документа");
        doc.setText("Текс документа");
        doc.setAuthor(dataGeneratorUtils.takeRandomPerson());
        doc.setRegDate(dateUtils.takeRandomDate(2000, 2020));
        doc.setRegId(random.nextInt(1000));

        return doc;
    }

/*
TODO разобраться с выводом, лучше вообще удалить и переписать

    public void printAll() {
    Map<String, List<Document>> docGrouped =
    setOfDocuments.stream().collect(Collectors.groupingBy(Document::getAuthor));

    docGrouped = new TreeMap<>(docGrouped);

    docGrouped.forEach((k,v) -> {
        System.out.print(k + ": \n");
        v.forEach(doc ->
            System.out.print(
                doc.getClass().getSimpleName() + " №" + doc.getId() + " от "
                    + doc.getRegDate() + ". " + doc.getName() + "\n")
        );
    });

    }
*/

}
