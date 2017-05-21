package lv.javaguru.java2.domain;

import lv.javaguru.java2.configs.LocalDatePersistenceConverter;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by VBarbasins on 30/03/17.
 */
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ReservationID", columnDefinition = "int(11)")
    private Long reservationID;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "DateFrom", columnDefinition = "date")
    private LocalDate dateFrom;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "DateTo", columnDefinition = "date")
    private LocalDate dateTo;

    @Column(name="AccountID", columnDefinition = "int(11)")
    private Long accountID;

    @Column(name="ResourceID", columnDefinition = "int(11)")
    private Long resourceID;

    @Column(name="Status", columnDefinition = "enum(`OPEN`,`CLOSED`)")
    @Enumerated(EnumType.STRING)
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

    public Long getAccountID() {
        return accountID;
    }

    public void setAccountID(Long accountID) {
        this.accountID = accountID;
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
