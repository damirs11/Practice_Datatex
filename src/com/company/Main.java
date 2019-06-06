package com.company;


import com.company.Exception.DocumentExistsException;
import com.company.Factory.DocumentFactory;
import com.company.Models.Documents.Document;
import com.company.Models.Documents.Incoming;
import com.company.Models.Documents.Outgoing;
import com.company.Models.Documents.Task;

public class Main {

    public static void main(String[] args) throws DocumentExistsException {

        Document outgoing = DocumentFactory.create(Outgoing.class);
        Document incoming = DocumentFactory.create(Incoming.class);
        Document task = DocumentFactory.create(Task.class);

        //TODO подключить логгер
        //TODO переписать toString() методы для моделей
        //TODO проверить табуляцию
        System.out.print(outgoing);
    }

}
