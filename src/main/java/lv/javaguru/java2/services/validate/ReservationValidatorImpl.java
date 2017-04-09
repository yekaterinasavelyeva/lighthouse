package lv.javaguru.java2.services.validate;

import javax.swing.text.DateFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;
import java.util.Locale;

/**
 * Created by user on 09.04.2017.
 */
public class ReservationValidatorImpl implements ReservationValidator {

    //TODO - how to check date format in tests with IllegalArgument exception?

    @Override
    public void validate(LocalDate dateFrom, LocalDate dateTo, Long libTicketID){
        validateDateFrom(dateFrom);
        validateDateFromHronology(dateFrom);
        validateDateTo(dateTo);
        validateDateToHronology(dateTo);
        validateTicketId(libTicketID);

    }

    private void validateDateFrom(LocalDate dateFrom) {
       checkIfDatesAreNull(dateFrom);
    }

    private void validateDateTo(LocalDate dateTo){
        checkIfDatesAreNull(dateTo);
    }

    private void validateDateFromHronology(LocalDate dateFrom){
        if (dateFrom.isAfter(LocalDate.now())||dateFrom.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Reservation Start Date must be set for today!");
        }
    }

    private void validateDateToHronology(LocalDate dateTo){
        if (dateTo.isBefore(LocalDate.now().plusDays(7))||dateTo.isAfter(LocalDate.now().plusDays(30))) {
            throw new IllegalArgumentException("Reservation End Date must be set for no less " +
                    "than 7 and no more than 30 days from now!");

        }
    }

    private void validateTicketId(Long libTicketId){
        if (libTicketId == null) {
            throw new IllegalArgumentException("Library Ticket ID must be not empty!");
        }
    }


    private void checkIfDatesAreNull(LocalDate date){
        if (date == null) {
            throw new IllegalArgumentException("Reservation dates must be not empty!");
        }
    }


    private String dateFormat = "yyyy-MM-dd";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);

    private void checkIfDatesConformToFormat(LocalDate date) {
        if(!isThisDateValid(date.toString())){
            throw new IllegalArgumentException("Reservation dates format do not " +
                    "conform to yyyy-MM-dd");
        }
    }

    @Override
    public boolean isThisDateValid(String dateToValidate){
        try {
            LocalDate validatedDate= LocalDate.parse(dateToValidate, formatter);
            System.out.println(validatedDate);
        } catch (DateTimeParseException ex){
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            return false;
        }
        return true;
    }

}
