package lv.javaguru.java2.domain;

import java.time.LocalDate;

/**
 * Created by VBarbasins on 30/03/17.
 */
public class ReservationBuilder {

    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long libTicketID;
    private ReservationStatus status;

    private ReservationBuilder() {}

    public static ReservationBuilder createReservation() {
        return new ReservationBuilder();
    }

    public Reservation build() {
        Reservation reservation = new Reservation();
        reservation.setDateFrom(dateFrom);
        reservation.setDateTo(dateTo);
        reservation.setLibTicketID(libTicketID);
        reservation.setStatus(status);
        return reservation;
    }

    public ReservationBuilder withDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public ReservationBuilder withDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public ReservationBuilder withLibTicketID(Long libTicketID) {
        this.libTicketID = libTicketID;
        return this;
    }

    public ReservationBuilder withStatus(ReservationStatus status) {
        this.status = status;
        return this;
    }
}
