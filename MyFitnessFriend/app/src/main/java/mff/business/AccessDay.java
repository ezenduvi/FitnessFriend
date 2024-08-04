package mff.business;

import mff.application.Main;
import mff.application.Services;
import mff.persistence.Database;

public class AccessDay {
    private static Database dataAccess;

    public AccessDay() {
        dataAccess = Services.getDataAccess(Main.dbName);
    }

    public static void addDay() {
        dataAccess.createDay();
    }

    public static int getNumOfDays() { return dataAccess.getNumOfDays(); }
}
