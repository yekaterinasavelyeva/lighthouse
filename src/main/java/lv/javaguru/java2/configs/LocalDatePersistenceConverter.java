package lv.javaguru.java2.configs;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by user on 21.05.2017.
 */
@Converter
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, java.sql.Date>{


        @Override
        public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
            if (entityValue != null) {
                return java.sql.Date.valueOf(entityValue);
            }
            return null;
        }

        @Override
        public LocalDate convertToEntityAttribute(java.sql.Date databaseValue) {
            if (databaseValue != null) {
                return databaseValue.toLocalDate();
            }
            return null;
        }
}
