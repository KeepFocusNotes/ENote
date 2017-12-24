package com.epam.university.spring.enote;

import com.epam.university.spring.enote.util.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        DateConverter dt = new DateConverter();
        log.info(dt.convertToDatabaseColumn(LocalDate.now()).toString());
    }
}
