package com.company.utils;


import com.company.enumeration.deliveryType;
import com.company.enumeration.docTypes;
import com.company.models.documents.Document;
import com.company.models.documents.Incoming;
import com.company.models.documents.Outgoing;
import com.company.models.documents.Task;
import org.apache.commons.lang.time.DateUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataGeneratorUtils {

    private static Random random = new Random();

    private DataGeneratorUtils() {
    }

    private static List<String> persons = Arrays.asList(
            "А Иванов Кузнецов",
            "Б Козлов Новиков",
            "В Кузнецов Соколов",
            "Г Лебедев Козлов",
            "Д Морозов Петров",
            "Е Новиков Морозов",
            "Ж Попов Лебедев",
            "З Смирнов Иванов",
            "И Соколов Попов"
    );

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static String takeRandomPerson() {
        return persons.get(random.nextInt(persons.size()));
    }

    public static String takeRandomDeliveryType() {
        return randomEnum(deliveryType.class).getType();
    }

    public static docTypes getRandomDocType() {
        return randomEnum(docTypes.class);
    }

    public static Date takeRandomDate(){
        return new Date(System.currentTimeMillis() - random.nextInt(1000 * 3600 * 24 * 1000));
    }

    public static Document generateRandomDataForDocument(Document doc){

        doc.setId(random.nextInt(1000));
        doc.setName("Название документа");
        doc.setText("Текс документа");
        doc.setAuthor(takeRandomPerson());
        doc.setRegDate(takeRandomDate());
        doc.setRegId(random.nextInt(1000));

        return doc;
    }

    public static Outgoing generateRandomDataForDocument(Outgoing doc){

        generateRandomDataForDocument( (Document) doc);

        doc.setAddressee(takeRandomPerson());
        doc.setDeliveryType(takeRandomDeliveryType());

        return doc;
    }

    public static Incoming generateRandomDataForDocument(Incoming doc){

        generateRandomDataForDocument( (Document) doc);

        doc.setOutgoingNumber("Номер");
        doc.setSender(takeRandomPerson());
        doc.setAddressee(takeRandomPerson());
        doc.setOutgoingDate(takeRandomDate());

        return doc;
    }

    public static Task generateRandomDataForDocument(Task doc){

        generateRandomDataForDocument( (Document) doc);

        doc.setController(takeRandomPerson());
        doc.setDateRealize(takeRandomDate());
        doc.setResponsibleExecutor(takeRandomPerson());
        doc.setPeriodOfExecution(DateUtils.addDays(doc.getDateRealize(), 30));
        doc.setSignOfControllability("Признак контрольности");

        return doc;
    }
}
