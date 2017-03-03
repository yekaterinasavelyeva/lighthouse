package lv.javaguru.java2.database.jdbc;

import lv.javaguru.java2.database.DBException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtil extends DAOImpl {

    void setupDatabaseFromFile(String fileName) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                driverClass, jdbcUrl, userName, password);

        databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);

        IDataSet dataSet = loadDataSetFromFile(fileName);

        databaseTester.setDataSet(dataSet);
        databaseTester.onSetup();
    }

    private IDataSet loadDataSetFromFile(String fileName) throws DataSetException {
        InputStream dataset = DatabaseUtil.class.getClassLoader().getResourceAsStream(fileName);
        return new FlatXmlDataSetBuilder().build(dataset);
    }

    public ITable loadTableFromDatabase(String tableName) throws Exception {
        IDatabaseTester databaseTester = new JdbcDatabaseTester(
                driverClass, jdbcUrl, userName, password);
        IDatabaseConnection connection = databaseTester.getConnection();
        return connection.createDataSet().getTable(tableName);
    }

    public ITable loadTableFromXMLDataSet(String fileWithExpectedDataSet,
                                           String tableName) throws Exception {
        InputStream dataset = DatabaseUtil.class.getClassLoader().getResourceAsStream(fileWithExpectedDataSet);
        IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(dataset);
        return expectedDataSet.getTable(tableName);
    }

    public void cleanDatabase() throws DBException {
        for(String tableName : getTableNames()) {
            Connection connection = getConnection();
            try {
                connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from " + tableName);
                preparedStatement.executeUpdate();
            } catch (Throwable e) {
                System.out.println("Exception while execute cleanDatabase() for table " + tableName);
                e.printStackTrace();
                throw new DBException(e);
            } finally {
                closeConnection(connection);
            }
        }
    }

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("USERS");
        return tableNames;
    }

}
