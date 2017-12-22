package com.epam.university.spring.enote.util;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Converter(autoApply = true)
public class DateConverter implements AttributeConverter<LocalDate, Timestamp> {

@Override
public Timestamp convertToDatabaseColumn(LocalDate localDate) {
    Timestamp result = null;

    if (localDate != null) {
    LocalDateTime localDateTime = localDate.atStartOfDay();
    result = Timestamp.valueOf(localDateTime);
    }

    return result;
    }

@Override
public LocalDate convertToEntityAttribute(Timestamp timestamp) {
    LocalDate result = null;

    if (timestamp != null) {
    LocalDateTime localDateTime = timestamp.toLocalDateTime();
    result = localDateTime.toLocalDate();
    }

    return result;
    }
}
