package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import lv.javaguru.java2.database.ResourceDAO;
import lv.javaguru.java2.domain.Resource;
import lv.javaguru.java2.domain.ResourceType;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by VBarbasins on 27/03/17.
 */
@Component("JdbcResourceDAO")
public class ResourceDAOImpl extends DAOImpl implements ResourceDAO {

    @Override
    public Resource save(Resource resource) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into RESOURCES values (default, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, resource.getResourceType().name());
            preparedStatement.setString(2, resource.getTitle());
            preparedStatement.setString(3, resource.getAuthor());
            preparedStatement.setInt(4, resource.getReleaseYear());

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                resource.setResourceID(rs.getLong(1));
            }
        } catch (Throwable e) {
            System.out.println("Exception while execute ResourceDAOImpl.save");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

        return resource;
    }

    @Override
    public Optional<Resource> getByID(Long id) throws DBException {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from RESOURCES where ResourceID = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Resource resource = null;
            if (resultSet.next()) {
                resource = new Resource();
                resource.setResourceID(
                        resultSet.getLong(
                                "ResourceID"));
                resource.setResourceType(ResourceType.valueOf(
                        resultSet.getString(
                                "ResourceType")));
                resource.setAuthor(
                        resultSet.getString(
                                "Author"));
                resource.setTitle(
                        resultSet.getString(
                                "Title"));
                resource.setReleaseYear(
                        resultSet.getInt(
                                "ReleaseYear"));
            }
            return Optional.ofNullable(resource);
        } catch (Throwable e) {
            System.out.println("Exception while execute ResourceDAOImpl.getByID");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(Long id) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from RESOURCES where ResourceID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute ResourceDAOImpl.delete");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }

    }

    @Override
    public void update(Resource resource) {
        Connection connection = null;

        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update RESOURCES set " +
                            "ResourceType = ?, " +
                            "Title = ?, " +
                            "Author = ?, " +
                            "ReleaseYear = ? " +
                            "where ResourceID = ? ");
            preparedStatement.setString(1,
                    resource.getResourceType().name());
            preparedStatement.setString(2,
                    resource.getTitle());
            preparedStatement.setString(3,
                    resource.getAuthor());
            preparedStatement.setInt(4,
                    resource.getReleaseYear());
            preparedStatement.setLong(5,
                    resource.getResourceID());
            preparedStatement.executeUpdate();
        } catch (Throwable e) {
            System.out.println("Exception while execute ResourceDAOImpl.delete");
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public List<Resource> getAll() {
        List<Resource> resources = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("select * from RESOURCES");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Resource resource = new Resource();
                resource.setResourceID(
                        resultSet.getLong(
                                "ResourceID"));
                resource.setResourceType(ResourceType.valueOf(
                        resultSet.getString(
                                "ResourceType")));
                resource.setAuthor(
                        resultSet.getString(
                                "Author"));
                resource.setTitle(
                        resultSet.getString(
                                "Title"));
                resource.setReleaseYear(
                        resultSet.getInt(
                                "ReleaseYear"));
                resources.add(resource);
            }

            return resources;
        } catch (Throwable e) {
            System.out.println("Exception while expecute ResourceDAOImpl.getAll");
            e.printStackTrace();
            throw new DBException(e);
        } finally {
            closeConnection(connection);
        }
    }
}

