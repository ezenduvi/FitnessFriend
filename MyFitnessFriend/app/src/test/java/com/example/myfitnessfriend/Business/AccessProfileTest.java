package com.example.myfitnessfriend.Business;

import org.junit.Test;

import mff.application.Main;
import mff.application.Services;
import mff.business.AccessProfile;
import mff.objects.User;
import mff.persistence.HSQLDB;

import static org.junit.Assert.assertEquals;

public class AccessProfileTest {

    private HSQLDB dataAccess;
    private AccessProfile accessProfile;

    public void setUp() {
        Services.createDataAccess(new HSQLDB(Main.dbName));
        dataAccess = (HSQLDB) Services.getDataAccess(Main.dbName);
        dataAccess.createSaveState();
        accessProfile = new AccessProfile();
    }

    public void tearDown() {
        dataAccess.rollbackSaveState();
        Services.closeDataAccess();
    }


    @Test
    public void testGetUser(){new User("fitness foodie", "test@gmail.com",2000, 3);

        System.out.println("\nStarting test testGetUser()");
        setUp();

        User user = accessProfile.getUser();

        assertEquals(user.getUserName(), "John Doe");
        assertEquals("example@test.com", user.getUserEmail());
        assertEquals(2345, user.getCalorieGoal());
        assertEquals(12, user.getWaterGoal());

        Services.closeDataAccess();
        System.out.println("\nFinished test testGetUser()");
    }

    @Test
    public void testGetUserName(){new User("fitness foodie", "test@gmail.com",2000, 3);

        System.out.println("\nStarting test testGetUserName()");
        setUp();

        String userName = accessProfile.getUserName();

        assertEquals("John Doe", userName);

        tearDown();
        System.out.println("\nFinished test testGetUserName()");
    }

    @Test
    public void testGetUserEmail(){new User("fitness foodie", "test@gmail.com",2000, 3);

        System.out.println("\nStarting test testGetUserEmail()");
        setUp();

        String userEmail = accessProfile.getUserEmail();

        assertEquals("example@test.com", userEmail);

        tearDown();
        System.out.println("\nFinished test testGetUserEmail()");
    }

    @Test
    public void testGetCalorieGoal(){

        System.out.println("\nStarting test testGetCalorieGoal()");
        setUp();

        String calorieGoal = accessProfile.getCalorieGoal();

        assertEquals("2345 calories", calorieGoal);

        tearDown();
        System.out.println("\nFinished test testGetCalorieGoal()");
    }

    @Test
    public void testGetWaterGoal(){new User("fitness foodie", "test@gmail.com",2000, 3);

        System.out.println("\nStarting test testGetWaterGoal()");
        setUp();

        String waterGoal = accessProfile.getUserWaterGoal();

        assertEquals("12 cups", waterGoal);

        tearDown();
        System.out.println("\nFinished test testGetWaterGoal()");
    }

}
