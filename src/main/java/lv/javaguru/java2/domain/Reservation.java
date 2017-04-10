package lv.javaguru.java2.domain;

import java.time.LocalDate;

/**
 * Created by VBarbasins on 30/03/17.
 */
public class Reservation {

    // TODO: 30/03/17 add Long ResourceID to all assosiated classes and tests

    private Long reservationID;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private Long libTicketID;
    private Long resourceID;
    private ReservationStatus status;

    public Long getReservationID() {
        return reservationID;
    }

    public void setReservationID(Long reservationID) {
        this.reservationID = reservationID;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public Long getLibTicketID() {
        return libTicketID;
    }

    public void setLibTicketID(Long libTicketID) {
        this.libTicketID = libTicketID;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Long getResourceID() {
        return resourceID;
    }

    public void setResourceID(Long resourceID) {
        this.resourceID = resourceID;
    }
}
