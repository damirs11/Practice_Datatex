package com.company.Utils;


import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

    private static List<String> deliveryType = Arrays.asList(
            "Способ доставки 1",
            "Способ доставки 2",
            "Способ доставки 3",
            "Способ доставки 4"
    );

    private static List<Class<? extends Document>> docTypes = Arrays.asList(
            Outgoing.class,
            Incoming.class,
            Task.class
    );

    public List getPersons() {
        return persons;
    }

    public List getDList() {
        return deliveryType;
    }

    public static String takeRandomPerson() {
        return persons.get(random.nextInt(persons.size()));
    }

    public static String takeRandomDeliveryType() {
        return deliveryType.get(random.nextInt(deliveryType.size()));
    }

    public static Class<? extends Document> getRandomDocType() {
        return docTypes.get(random.nextInt(docTypes.size()));
    }
    public static Date takeRandomDate(int lowerBound, int upperBound){

        GregorianCalendar gc = new GregorianCalendar();

        int year = random.nextInt(upperBound - lowerBound) + lowerBound;
        int dayOfYear = random.nextInt(gc.getActualMaximum(Calendar.DAY_OF_YEAR)) + 1;

        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        return gc.getTime();
    }

    public static Date addDays(Date date, int days){

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, days);

        return calendar.getTime();
    }

    public static Document generateRandomDataForDocument(Document doc){

        int docId = random.nextInt(1000);
        int regId = random.nextInt(1000);

        doc.setId(docId);
        doc.setName("Название документа");
        doc.setText("Текс документа");
        doc.setAuthor(takeRandomPerson());
        doc.setRegDate(takeRandomDate(2000, 2020));
        doc.setRegId(regId);

        return doc;
    }

    public static Outgoing generateRandomDataForOutgoing(Outgoing doc){

        doc.setAddressee(takeRandomPerson());
        doc.setDeliveryType(takeRandomDeliveryType());

        return doc;
    }

    public static Incoming generateRandomDataForIncoming(Incoming doc){

        doc.setOutgoingNumber("Номер");
        doc.setSender(takeRandomPerson());
        doc.setAddressee(takeRandomPerson());
        doc.setOutgoingDate(takeRandomDate(2000, 2020));

        return doc;
    }

    public static Task generateRandomDataForTask(Task doc){

        doc.setController(takeRandomPerson());
        doc.setDateRealize(takeRandomDate(2000, 2020));
        doc.setResponsibleExecutor(takeRandomPerson());
        doc.setPeriodOfExecution(addDays(doc.getDateRealize(), 30));
        doc.setSignOfControllability("Признак контрольности");

        return doc;
    }
}
