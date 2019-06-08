package com.company.Utils;


import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DataGeneratorUtils {

    private static Random random = new Random();

    private DataGeneratorUtils() {
    }

    private enum persons {
        PERSONS1 ("А Иванов Кузнецов"),
        PERSONS2 ("А Иванов Кузнецов"),
        PERSONS3 ("В Кузнецов Соколов"),
        PERSONS4 ("Г Лебедев Козлов"),
        PERSONS5 ("Д Морозов Петров"),
        PERSONS6 ("Е Новиков Морозов"),
        PERSONS7 ("Ж Попов Лебедев"),
        PERSONS8 ("З Смирнов Иванов"),
        PERSONS9 ("И Соколов Попов");

        private final String person;

        persons(String s) {
            this.person = s;
        }

        public String getPerson() {
            return person;
        }
    }

    private enum deliveryType {
        TYPE1 ("Способ доставки 1"),
        TYPE2 ("Способ доставки 2"),
        TYPE3 ("Способ доставки 3"),
        TYPE4 ("Способ доставки 4");

        private final String type;

        deliveryType(String s) {
            this.type = s;
        }

        public String getType() {
            return type;
        }
    }

    private enum docTypes {
        OUTGOING (Outgoing.class),
        INCOMING (Incoming.class),
        TASK (Task.class);

        private final Class<? extends Document> type;

        docTypes(Class<? extends Document> aClass) {
            this.type = aClass;
        }

        public Class<? extends Document> getType() {
            return type;
        }
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        Integer x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static String takeRandomPerson() {
        return randomEnum(persons.class).getPerson();
    }

    public static String takeRandomDeliveryType() {
        return randomEnum(deliveryType.class).getType();
    }

    public static Class<? extends Document> getRandomDocType() {
        return randomEnum(docTypes.class).getType();
    }
    public static Date takeRandomDate(){
        return new Date(System.currentTimeMillis() - ThreadLocalRandom.current().nextLong(1000*60*60*24*1000));
    }

    public static Document generateRandomDataForDocument(Document doc){

        Integer docId = random.nextInt(1000);
        Integer regId = random.nextInt(1000);

        doc.setId(docId);
        doc.setName("Название документа");
        doc.setText("Текс документа");
        doc.setAuthor(takeRandomPerson());
        doc.setRegDate(takeRandomDate());
        doc.setRegId(regId);

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
