package com.company.Factory;

import com.company.Objects.Document;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class DocumentFactory {
    public abstract Document create();
    public Document generateData(Document doc){

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

        doc.setIdDoc((int) (Math.random() * 1000));
        doc.setName("Название документа");
        doc.setText("Текс документа");
        doc.setReg_idDoc((int) (Math.random() * 1000));
        doc.setAuthor( names[(int) (Math.random() * (names.length - 1))] );

        GregorianCalendar gc = new GregorianCalendar();
        int year = 2010 + (int) Math.round(Math.random() * (2020 - 2010));
        gc.set(Calendar.YEAR, year);
        int dayOfYear = 1 + (int) Math.round(Math.random() * (gc.getActualMaximum(Calendar.DAY_OF_YEAR) - 1));
        gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

        doc.setRegDate(gc.getTime());

        return doc;
    }
}
