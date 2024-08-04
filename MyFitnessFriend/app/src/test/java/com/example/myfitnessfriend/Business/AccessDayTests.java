package com.example.myfitnessfriend.Business;

import org.junit.Test;

import mff.application.Main;
import mff.application.Services;
import mff.business.AccessDay;
import mff.persistence.HSQLDB;

import static org.junit.Assert.assertEquals;

public class AccessDayTests {
    private HSQLDB dataAccess;
    private AccessDay accessDay;

    public void setUp() {
        Services.createDataAccess(new HSQLDB(Main.dbName));
        accessDay = new AccessDay();
        dataAccess = (HSQLDB) Services.getDataAccess(Main.dbName);
        dataAccess.open(Main.getDBPathName());
        dataAccess.createSaveState();
    }

    public void tearDown() {
        dataAccess.rollbackSaveState();
        dataAccess.close();
    }

    @Test
    public void testAddOneDay() {
        int getStartingDayNum;
        System.out.println("\nStarting test testAddOneDay()");
        setUp();

        getStartingDayNum = AccessDay.getNumOfDays();

        AccessDay.addDay();
        assertEquals(getStartingDayNum + 1, AccessDay.getNumOfDays());

        System.out.println(AccessDay.getNumOfDays());
        tearDown();
        System.out.println("\nFinished test testAddOneDay()");
    }

    @Test
    public void testAddManyDays() {
        int getStartingDayNum;
        System.out.println("\nStarting test testAddManyDays()");
        setUp();

        getStartingDayNum = AccessDay.getNumOfDays();

        AccessDay.addDay();
        assertEquals(getStartingDayNum + 1, AccessDay.getNumOfDays());
        AccessDay.addDay();
        AccessDay.addDay();
        assertEquals(getStartingDayNum + 3, AccessDay.getNumOfDays());

        tearDown();
        System.out.println("\nFinished test testAddManyDays()");
    }
}
