package lv.javaguru.java2.domain;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lv.javaguru.java2.configs.LocalDatePersistenceConverter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by VBarbasins on 30/03/17.
 */
@Entity
@Table(name = "reservations")
@TypeDef(
        name = "pgsql_enum_reserv",
        typeClass = PostgreSQLEnumType.class
)
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ReservationID", columnDefinition = "serial")
    private Long reservationID;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "DateFrom", columnDefinition = "date")
    private LocalDate dateFrom;

    @Convert(converter = LocalDatePersistenceConverter.class)
    @Column(name = "DateTo", columnDefinition = "date")
    private LocalDate dateTo;

    @Column(name="AccountID", columnDefinition = "int4")
    private Long accountID;

    @Column(name="ResourceID", columnDefinition = "int4")
    private Long resourceID;

    @Column(name="Status", columnDefinition = "resourcestatus")
    @Enumerated(EnumType.STRING)
    @Type( type = "pgsql_enum_reserv" )
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
