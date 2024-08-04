package mff.application;

import mff.persistence.DataAccessStub;
import mff.persistence.HSQLDB;

public class Main {

    public static final String dbName = "foodDB";
    public static String dbPathName = "database/foodDB";

    /**
     * main
     * starts calls start up and shut down to run app
     * @param args
     */
    public static void main(String[] args) {
        startUp();
        shutDown();
        System.out.println("All done");
    }

    /**
     * startUp
     * creates data access to start app
     */
    public static void startUp() {
        // for dependency injection, switch which of the following two lines are commented out
        //Acceptance tests only work with hsqldb
        Services.createDataAccess(new HSQLDB(dbName));
//        Services.createDataAccess(dbName);
    }

    /**
     * shutDown
     * closes data access to shut down app
     */
    public static void shutDown() {
        Services.closeDataAccess();
    }

    /**
     * getDBPathName
     * gets database path name
     */
    public static String getDBPathName() {
        if (dbPathName == null)
            return dbName;
        else
            return dbPathName;
    }

    /**
     * setDBPathName
     * sets database path name
     * @param pathName - name given to database path name
     */
    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }

}
