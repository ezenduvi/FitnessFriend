package com.example.myfitnessfriend.Integration;

import com.example.myfitnessfriend.Persistence.DatabaseTest;

import junit.framework.TestCase;

import mff.application.Main;
import mff.application.Services;
import mff.persistence.Database;
import mff.persistence.HSQLDB;

public class DatabaseHSQLDBTest extends TestCase {

    private static String dbName = Main.dbName;

    public DatabaseHSQLDBTest(String arg0)
    {
        super(arg0);
    }

    public void testDataAccess()
    {
        Database database;

        Services.closeDataAccess();

        System.out.println("\nStarting Integration test Database (using real DB)");

        database = Services.createDataAccess(new HSQLDB(dbName));

        DatabaseTest.databaseTest(database);

        Services.closeDataAccess();

        System.out.println("Finished Integration test Database (using real DB)");
    }
}
