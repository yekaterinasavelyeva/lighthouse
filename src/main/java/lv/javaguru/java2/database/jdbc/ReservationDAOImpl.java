package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ReservationDAO;
import lv.javaguru.java2.domain.Reservation;
import lv.javaguru.java2.domain.ReservationStatus;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by VBarbasins on 30/03/17.
 */
public class ReservationDAOImpl extends DAOImpl  implements ReservationDAO {
    @Override
    public Reservation save(Reservation reservation) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into RESERVATIONS values (default, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setDate(1, Date.valueOf(reservation.getDateFrom()));
            preparedStatement.setDate(2, Date.valueOf(reservation.getDateTo()));
            preparedStatement.setLong(3, reservation.getLibTicketID());
            preparedStatement.setLong(4, reservation.getResourceID());
            preparedStatement.setString(5, reservation.getStatus().name());

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                reservation.setReservationID(resultSet.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while ReservationDAOImpl.save");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
        return reservation;
    }

    @Override
    public Optional<Reservation> getByID(Long reservationID) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from RESERVATIONS where ReservationID = ?");
            preparedStatement.setLong(1, reservationID);
            ResultSet resultSet = preparedStatement.executeQuery();
            Reservation reservation = null;
            if (resultSet.next()) {
                reservation = new Reservation();
                reservation.setReservationID(
                        resultSet.getLong(
                                "ReservationID"));
                reservation.setDateFrom(
                        resultSet.getDate(
                                "DateFrom").toLocalDate());
                reservation.setDateTo(
                        resultSet.getDate(
                                "DateTo").toLocalDate());
                reservation.setLibTicketID(
                        resultSet.getLong(
                                "LibTicketID"));
                reservation.setResourceID(resultSet.getLong("ResourceID"));
                reservation.setStatus(ReservationStatus.valueOf(
                        resultSet.getString(
                                "Status")));
            }
            return Optional.ofNullable(reservation);
        } catch (Throwable e) {
            System.out.println("Exception while execute ReservationDAOImpl.getByID");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void update(Reservation reservation) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update RESERVATIONS set " +
                            "DateFrom = ?, " +
                            "DateTo = ?, " +
                            "LibTicketID = ?, " +
                            "ResourceID = ?, " +
                            "Status = ? " +
                            "where ReservationID = ? ");
            preparedStatement.setDate(1,
                    Date.valueOf(reservation.getDateFrom()));
            preparedStatement.setDate(2,
                    Date.valueOf(reservation.getDateTo()));
            preparedStatement.setLong(3,
                    reservation.getLibTicketID());
            preparedStatement.setLong(4,
                    reservation.getResourceID());
            preparedStatement.setString(5,
                    reservation.getStatus().name());
            preparedStatement.setLong(6,
                    reservation.getReservationID());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute ReservationDAOImpl.update");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Reservation> getByLibTicketID(Long libTicketID) {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from RESERVATIONS " +
                            "where LibTicketID = ?");
            preparedStatement.setLong(1, libTicketID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationID(
                        resultSet.getLong(
                                "ReservationID"));
                reservation.setDateFrom(
                        resultSet.getDate(
                                "DateFrom").toLocalDate());
                reservation.setDateTo(
                        resultSet.getDate(
                                "DateTo").toLocalDate());
                reservation.setLibTicketID(
                        resultSet.getLong(
                                "LibTicketID"));
                reservation.setResourceID(
                        resultSet.getLong(
                                "ResourceID"));
                reservation.setStatus(ReservationStatus.valueOf(
                        resultSet.getString(
                                "Status")));
                reservations.add(reservation);
            }
            return reservations;
        } catch (Throwable e) {
            System.out.println("Exception while execute ReservationDAOImpl.getByLibTicketID");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Reservation> getByResourceID(Long resourceID) {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from RESERVATIONS " +
                            "where ResourceID = ?");
            preparedStatement.setLong(1, resourceID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationID(
                        resultSet.getLong(
                                "ReservationID"));
                reservation.setDateFrom(
                        resultSet.getDate(
                                "DateFrom").toLocalDate());
                reservation.setDateTo(
                        resultSet.getDate(
                                "DateTo").toLocalDate());
                reservation.setLibTicketID(
                        resultSet.getLong(
                                "LibTicketID"));
                reservation.setResourceID(
                        resultSet.getLong(
                                "ResourceID"));
                reservation.setStatus(ReservationStatus.valueOf(
                        resultSet.getString(
                                "Status")));
                reservations.add(reservation);
            }
            return reservations;
        } catch (Throwable e) {
            System.out.println("Exception while execute ReservationDAOImpl.getByResourceID");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from RESERVATIONS ");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationID(
                        resultSet.getLong(
                                "ReservationID"));
                reservation.setDateFrom(
                        resultSet.getDate(
                                "DateFrom").toLocalDate());
                reservation.setDateTo(
                        resultSet.getDate(
                                "DateTo").toLocalDate());
                reservation.setLibTicketID(
                        resultSet.getLong(
                                "LibTicketID"));
                reservation.setResourceID(
                        resultSet.getLong(
                                "ResourceID"));
                reservation.setStatus(ReservationStatus.valueOf(
                        resultSet.getString(
                                "Status")));
                reservations.add(reservation);
            }
            return reservations;
        } catch (Throwable e) {
            System.out.println("Exception while execute ReservationDAOImpl.getAll");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

}
