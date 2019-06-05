package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class IncomingFactory extends DocumentFactory {


    Document create() throws DocumentExistsException {
        Incoming doc = new Incoming();

        doc = (Incoming) generateData(doc);

        return doc;
    }

    @Override
    public Document generateData(Document doc) throws DocumentExistsException {

        String[] names = {
                "Смирнов Иванов",
                "Иванов Кузнецов",
                "Кузнецов Соколов",
                "Соколов Попов",
                "Попов Лебедев",
                "Лебедев Козлов",
                "Козлов Новиков",
                "Новиков Морозов",
                "Морозов Петров",
                "Петров Смирнов"
        };


        GregorianCalendar gc = new GregorianCalendar();
        int year = 2010 + (int) Math.round(Math.random() * (2020 - 2010));
        int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(Calendar.DAY_OF_YEAR) - 1));
        gc.set(Calendar.YEAR, year);
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);


        Incoming newDoc = (Incoming) super.generateData(doc);

        newDoc.setAddressee( names[(int) (Math.random() * (names.length - 1))] );
        newDoc.setOutgoingNumber("Номер");
        newDoc.setSender( names[(int) (Math.random() * (names.length - 1))] );
        newDoc.setOutgoingDate(gc.getTime());



        return newDoc;
    }
}
