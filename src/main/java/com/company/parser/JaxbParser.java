package com.company.parser;

import com.company.models.staff.ListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * The type Jaxb parser.
 */
public class JaxbParser {

    private JaxbParser(){
        throw new IllegalStateException();
    }

    /**
     * Gets data from file and translate it to specify Class<T>
     *
     * @param file Path to the file
     * @param clazz Class of object being unmarshaled
     * @return ListWrapper<clazz>
     * @throws JAXBException the jaxb exception
     */
    public static <T> ListWrapper<T> getObject(File file, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(ListWrapper.class, clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        return (ListWrapper<T>) unmarshaller.unmarshal(file);
    }

    /**
     * Save object which wrapped into generic ListWrapper<T> to @param file
     *
     * @param file Path to the file
     * @param list ListWrapper<T> which will be saved into @param file
     * @throws JAXBException the jaxb exception
     */
    public static <T> void saveObject(File file, ListWrapper<T> list) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(list.getClass(), list.getList().get(0).getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(list, file);
    }
}

