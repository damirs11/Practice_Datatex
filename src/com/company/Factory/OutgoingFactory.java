package com.company.Factory;

import com.company.Objects.Document;
import com.company.Objects.Outgoing;

import java.util.Date;

public class OutgoingFactory extends DocumentFactory{
    @Override
    public Document create() {
        Outgoing doc = new Outgoing();

        doc = (Outgoing) generateData(doc);

        return doc;
    }

    @Override
    public Document generateData(Document doc) {

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

        String[] deliveryType = {
            "Способ доставки 1",
            "Способ доставки 2",
            "Способ доставки 3"
        };

        Outgoing newDoc = (Outgoing) super.generateData(doc);
        newDoc.setAddressee( names[(int) (Math.random() * (names.length - 1))] );
        newDoc.setDeliveryType( deliveryType[(int) (Math.random() * (deliveryType.length - 1))] );
        return newDoc;
    }
}
