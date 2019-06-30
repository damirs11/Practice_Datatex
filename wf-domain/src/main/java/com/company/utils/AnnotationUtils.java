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

/**
 * The type Annotation utils.
 */
public class AnnotationUtils {

    /**
     * Gets question marks for insert.
     *
     * @param clazz the clazz
     * @return the question marks for insert
     */
    public static String getQuestionMarksForInsert(Class clazz) {
        return ReflectionUtils.getDeclaredFieldsIncludingInherited(clazz)
                .stream()
                .filter(field -> field.isAnnotationPresent(Column.class))
                .map(field -> "?")
                .collect(Collectors.joining(", "));
    }

    /**
     * Gets table name from annotation
     *
     * @param clazz target
     * @return the table name
     */
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

    /**
     * Gets column name from annotation
     *
     * @param annotatedField the annotated field
     * @return the column name
     */
    public static String getColumnName(Field annotatedField) {
        String string;
        if (annotatedField.getAnnotation(Column.class).value().equals("")) {
            string = annotatedField.getName();
        } else {
            string = annotatedField.getAnnotation(Column.class).value();
        }
        return string;
    }

    /**
     * Gets annotated fields with @Column annotation and
     * return their with or not type definition translated
     * to Apache Derby dialect
     *
     * @param clazz            target
     * @param typesInputEnable input type in result String
     * @return String with or not type definition
     */
    public static <T> String getAnnotatedColumnFields(Class<T> clazz, boolean typesInputEnable) {
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

    /**
     * Check table for existence
     *
     * @param connection the connection
     * @param clazz      target
     * @return true - table exists / false - table not exists
     * @throws SQLException the sql exception
     */
    public static <T> boolean getTableExists(Connection connection, Class<T> clazz) throws SQLException {
        DatabaseMetaData meta = connection.getMetaData();
        ResultSet result = meta.getTables(null, null, getTableName(clazz), null);
        return result.next();
    }

    /**
     * @param javaType default java type
     * @return translated java type to derby
     */
    private static String getDerbyType(String javaType) {
        switch (javaType) {
            case "String":
                return "VARCHAR(50)";
            default:
                return javaType;
        }
    }
}
