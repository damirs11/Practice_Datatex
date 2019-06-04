package com.company.Factory;

import com.company.Exception.DocumentExistsException;
import com.company.Models.Document;
import com.company.Models.Incoming;
import com.company.Models.Task;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TaskFactory extends DocumentFactory {

    Document create() throws DocumentExistsException {
        Task doc = new Task();

        doc = (Task) generateData(doc);

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


        Task newDoc = (Task) super.generateData(doc);

        newDoc.setController( names[(int) (Math.random() * (names.length - 1))] );
        newDoc.setDateRealize(gc.getTime());
        newDoc.setResponsibleExecutor( names[(int) (Math.random() * (names.length - 1))] );

        gc.add(GregorianCalendar.MONTH, 2);
        newDoc.setPeriodOfExecution(gc.getTime());
        newDoc.setSignOfControllability("Признак контрольности");

        return newDoc;
    }

}
