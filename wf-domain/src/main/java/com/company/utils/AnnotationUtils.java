package com.company.utils;

import com.company.annotation.Column;
import com.company.annotation.Table;
import org.apache.hadoop.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class AnnotationUtils {

    public static String getQuestionMarksForInsert(Class clazz) {
        return ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz)
                .stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> "?")
                .collect(Collectors.joining(", "));
    }

    public static <T> String getTableName(Class<T> clazz) {
        String tableName = null;
        if (clazz.isAnnotationPresent(Table.class)) {
            if (clazz.getAnnotation(Table.class).value().equals("")) {
                tableName = clazz.getSimpleName().toUpperCase();
            } else {
                tableName = clazz.getAnnotation(Table.class).value().toUpperCase();
            }
        }
        return tableName;
    }

    public static String getColumnName(Field annotatedField) {
        String string;
        if (annotatedField.getAnnotation(Column.class).value().equals("")) {
            string = annotatedField.getName();
        } else {
            string = annotatedField.getAnnotation(Column.class).value();
        }
        return string;
    }

    public static <T> String getAnnotatedFields(Class<T> clazz, boolean typesInputEnable) {
        return ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz).stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> {
                    if (typesInputEnable) {
                        return String.format("%s %s", getColumnName(field), getDerbyType(field.getType().getSimpleName()));
                    } else {
                        return String.format("%s", getColumnName(field));
                    }
                })
                .collect(Collectors.joining(", "));
    }

    public static <T> boolean getTableExists(Connection connection, Class<T> clazz) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet result = meta.getTables(null, null, getTableName(clazz), null);
        return result.next();
    }

    private static String getDerbyType(String javaType) {
        switch (javaType.toUpperCase()) {
            case "STRING":
                return "VARCHAR(50)";
            default:
                return javaType;
        }
    }
}
