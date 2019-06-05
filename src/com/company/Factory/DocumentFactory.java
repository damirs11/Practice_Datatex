package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;

import java.util.*;
import java.util.stream.Collectors;

public class DocumentFactory  {

    public DocumentFactory() {
        setOfDocuments = new ArrayList<>();
    }

    public Document create(Class<? extends Document> docType) throws DocumentExistsException {

        Document doc = null;

        if(Objects.equals(docType.getName(), Outgoing.class.getName() ))
            doc = new OutgoingFactory().create();

        if(Objects.equals(docType.getName(), Incoming.class.getName() ))
            doc = new IncomingFactory().create();

        if(Objects.equals(docType.getName(), Task.class.getName() ))
            doc = new TaskFactory().create();

        if(doc != null)
            setOfDocuments.add(doc);

        return doc;
    }
    public Document generateData(Document doc) throws DocumentExistsException {

        String[] names = {
                "А Иванов Кузнецов",
                "Б Козлов Новиков",
                "В Кузнецов Соколов",
                "Г Лебедев Козлов",
                "Д Морозов Петров",
                "Е Новиков Морозов",
                "Ж Попов Лебедев",
                "З Смирнов Иванов",
                "И Соколов Попов"
        };

        GregorianCalendar gc = new GregorianCalendar();
        int year = 2010 + (int) Math.round(Math.random() * (2020 - 2010));
        int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(Calendar.DAY_OF_YEAR) - 1));
        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);


        doc.setIdDoc((int) (Math.random() * 1000));
        doc.setName("Название документа");
        doc.setText("Текс документа");
        doc.setAuthor( names[(int) (Math.random() * (names.length - 1))] );
        doc.setRegDate(gc.getTime());

        doc.setReg_idDoc((int) (Math.random() * 1000));

        for(Document elem: setOfDocuments){
            if(doc.getReg_idDoc() == elem.getReg_idDoc())
                throw new DocumentExistsException();
        }
        return doc;
    }

    public void printAll(){
        Map<String, List<Document> > docGrouped =
                setOfDocuments.stream().collect(Collectors.groupingBy(Document::getAuthor));

        docGrouped = new TreeMap<>(docGrouped);

        docGrouped.forEach((k,v) ->
        {
            System.out.print(k + ": \n");
                v.forEach((doc) ->
                        System.out.print(
                                doc.getClass().getSimpleName() + " №" + doc.getIdDoc() + " от "
                                + doc.getRegDate() + ". " + doc.getName() + "\n")
                );
        });

    }

    private List<Document> setOfDocuments;
}
