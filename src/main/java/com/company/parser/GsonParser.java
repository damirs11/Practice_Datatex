package com.company.parser;

import com.google.gson.Gson;

import java.io.*;
import java.lang.reflect.Type;

/**
 * The type Gson parser.
 */
public class GsonParser {

    private GsonParser() {
        throw new IllegalStateException();
    }

    /**
     * Gets object.
     *
     * @param file Path to the file
     * @param c    Class of object being serialized
     * @return the object which casted into @param c
     * @throws FileNotFoundException the file not found exception
     */
    public static Object getObject(File file, Class c) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(file), c.getClass());
    }

    /**
     * Gets object.
     *
     * @param file Path to the file
     * @param c    Type of object being serialized
     * @return the object which casted into @param c
     * @throws FileNotFoundException the file not found exception
     */
    public static Object getObject(File file, Type c) throws FileNotFoundException {
        Gson gson = new Gson();
        return gson.fromJson(new FileReader(file), c);
    }

    /**
     * Save object.
     *
     * @param file Path to the file where will be saved
     * @param o   Object which will be saved into @param file
     * @throws IOException the io exception
     */
    public static void saveObject(File file, Object o) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            Gson gson = new Gson();
            gson.toJson(o, writer);
        }
    }
}

