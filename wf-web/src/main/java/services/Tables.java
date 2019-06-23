package services;

public enum Tables {
    PERSONS("Persons"),
    DOCUMENTS("Documents");

    private String table;

    Tables(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }
}
