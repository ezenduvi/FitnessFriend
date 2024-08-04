package com.example.myfitnessfriend.Business;

import org.junit.Test;

import mff.application.Main;
import mff.application.Services;
import mff.business.AccessDiary;
import mff.objects.Diary;
import mff.persistence.HSQLDB;

import static org.junit.Assert.assertEquals;


public class AccessDiaryTests {
    private HSQLDB dataAccess;
    private AccessDiary accessDiary;

    public void setUp() {
        Services.createDataAccess(new HSQLDB(Main.dbName));
        accessDiary = new AccessDiary();
        dataAccess = (HSQLDB) Services.getDataAccess(Main.dbName);
        dataAccess.open(Main.getDBPathName());
        dataAccess.createSaveState();
    }

    public void tearDown() {
        dataAccess.rollbackSaveState();
        dataAccess.close();
    }

    @Test
    public void testInsertOneReflections(){
        int numOfReflections;
        System.out.println("\nStarting test addReflections()");
        setUp();

        numOfReflections = accessDiary.getReflections().size();

        accessDiary.addReflection(new Diary("today i went over my calorie Limit"));

        assertEquals(numOfReflections, accessDiary.getReflections().size());
        assertEquals("today i went over my calorie Limit", accessDiary.getReflections().get(accessDiary.getReflections().size() - 1).getReflection());

        tearDown();
        System.out.println("\nFinished test addReflections()");
    }

    @Test
    public void testInsertManyReflections(){
        int numOfReflections;
        System.out.println("\nStarting test addReflections()");
        setUp();

        numOfReflections = accessDiary.getReflections().size();

        accessDiary.addReflection(new Diary("today i went over my calorie Limit"));
        accessDiary.addReflection(new Diary("today i went under my calorie Limit"));

        assertEquals(numOfReflections, accessDiary.getReflections().size());
        assertEquals("today i went under my calorie Limit", accessDiary.getReflections().get(accessDiary.getReflections().size() - 1).getReflection());

        tearDown();
        System.out.println("\nFinished test addReflections()");
    }

}
