package mff.application;

import mff.persistence.DataAccessStub;
import mff.persistence.Database;

public class Services {

    private static Database dataAccessService = null;

    /**
     * createDataAccess
     * creates stub database if not created already
     * @param dbName - name of stub database
     */
    public static Database createDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            dataAccessService = new DataAccessStub(dbName);
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    /**
     * createDataAccess
     * creates HSQL database if not created already
     * @param alternateDatabase - HSQLDB database to be created
     */
    public static Database createDataAccess(Database alternateDatabase)
    {
        if (dataAccessService == null)
        {
            dataAccessService = alternateDatabase;
            dataAccessService.open(Main.getDBPathName());
        }
        return dataAccessService;
    }

    /**
     * getDataAccess
     * gets database
     * @param dbName - name of database to be returned
     * @return
     */
    public static Database getDataAccess(String dbName)
    {
        if (dataAccessService == null)
        {
            System.out.println("Connection to data access has not been established.");
            System.exit(1);
        }
        return dataAccessService;
    }

    /**
     * closeDataAccess
     * closes database
     */
    public static void closeDataAccess()
    {
        if (dataAccessService != null)
        {
            dataAccessService.close();
        }
        dataAccessService = null;
    }


}
