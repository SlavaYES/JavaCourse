package ru.sibsutis;

public interface CSV {
    String toCSV();
    void fromCSV(String name, String email, String param);
    // plus fill
    void fillCSV();
}
