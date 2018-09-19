package lv.javaguru.java2.database.jdbc;

import org.junit.Before;

public abstract class DBUnitTestCase {

    protected DatabaseUtil databaseUtil = new DatabaseUtil();


    @Before
    public void init() throws Exception {
        databaseUtil.setupDatabaseFromFile(getDatabaseFile(),getSchemaName());
    }

    protected abstract String getDatabaseFile();
    protected abstract String getSchemaName();

}
