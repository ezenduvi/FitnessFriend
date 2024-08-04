package com.example.myfitnessfriend.Objects;


import mff.objects.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testValidName() {
        User user1;
        User user2;

        System.out.println("\nStarting testValidName");

        user1 = new User("Jane Smith","test@email.com");
        user2 = new User("JS","test@email.com");

        assertEquals("Jane Smith", user1.getUserName());
        assertEquals("JS", user2.getUserName());

        System.out.println("Finished testValidName");
    }

    @Test
    public void testEmptyName() {
        User user1;
        User user2;

        System.out.println("\nStarting testEmptyName");

        user1 = new User("","test@email.com");
        user2 = new User("","test@email.com",1500,5);

        assertTrue(user1.getUserName() == "");
        assertTrue(user2.getUserName() == "");

        System.out.println("Finished testEmptyName");
    }

    @Test
    public void testNullName() {
        User user1;
        User user2;

        System.out.println("\nStarting testNullName");

        user1 = new User(null, "test@email.com");
        user2 = new User(null, "test@email.com", 1500, 5);

        assertEquals("no-name",user1.getUserName());
        assertEquals("no-name",user2.getUserName());

        System.out.println("Finished testNullName");

    }

    @Test
    public void testValidEmail() {
        User user1;
        User user2;
        User user3;

        System.out.println("\nStarting testValidEmail");

        user1 = new User("test","janesmith@gmail.com");
        user2 = new User("test","jane.smith@gmail.com");
        user3 = new User("test","jane81@icloud.com");

        assertEquals("janesmith@gmail.com", user1.getUserEmail());
        assertEquals("jane.smith@gmail.com", user2.getUserEmail());
        assertEquals("jane81@icloud.com", user3.getUserEmail());

        System.out.println("Finished testValidEmail");
    }

    @Test
    public void testInvalidEmail() {
        User user1;
        User user2;

        System.out.println("\nStarting testInvalidEmail");

        user1 = new User("test","janesmithgmailcom");
        user2 = new User("test","jane@81@icloud.com");

        assertEquals("no-email",user1.getUserEmail());
        assertEquals("no-email",user2.getUserEmail());

        System.out.println("Finished testInvalidEmail");
    }

    @Test
    public void testEmptyEmail() {
        User user1;
        User user2;

        System.out.println("\nStarting testEmptyEmail");

        user1 = new User("test","");
        user2 = new User("test","",1500,5);

        assertEquals("no-email", user1.getUserEmail());
        assertEquals("no-email", user2.getUserEmail());

        System.out.println("Finished testEmptyEmail");
    }

    @Test
    public void testNullEmail() {
        User user1;
        User user2;

        System.out.println("\nStarting testNullEmail");

        user1 = new User("test", null);
        user2 = new User("test",null,1500,5);

        assertEquals("no-email",user1.getUserEmail());
        assertEquals("no-email",user2.getUserEmail());

        System.out.println("Finished testNullEmail");

    }

    @Test
    public void testValidCalorieGoal() {
        User user1;
        User user2;
        User user3;

        System.out.println("\nStarting testValidCalorieGoal");

        user1 = new User("testName","test@email.com",0,5);
        user2 = new User("testName","test@email.com", 1, 5);
        user3 = new User("testName","test@email.com", 1500, 5);

        assertEquals(0,user1.getCalorieGoal());
        assertEquals(1,user2.getCalorieGoal());
        assertEquals(1500,user3.getCalorieGoal());

        System.out.println("Finished testValidCalorieGoal");
    }

    @Test
    public void testInvalidCalorieGoal() {
        User user1;
        User user2;

        System.out.println("\nStarting testInvalidCalorieGoal");

        user1 = new User("testName","test@email.com",-1,5);
        user2 = new User("testName","test@email.com", -1111, 5);

        assertEquals(0,user1.getCalorieGoal());
        assertEquals(0,user2.getCalorieGoal());

        System.out.println("Finished testInvalidCalorieGoal");
    }

    @Test
    public void testValidWaterGoal() {
        User user1;
        User user2;
        User user3;

        System.out.println("\nStarting testValidWaterGoal");

        user1 = new User("testName","test@email.com",1500,0);
        user2 = new User("testName","test@email.com", 1500, 1);
        user3 = new User("testName","test@email.com", 1500, 10);

        assertEquals(0,user1.getWaterGoal());
        assertEquals(1, user2.getWaterGoal());
        assertEquals(10, user3.getWaterGoal());

        System.out.println("Finished testValidWaterGoal");
    }

    @Test
    public void testInvalidWaterGoal() {
        User user1;
        User user2;

        System.out.println("\nStarting testInvalidWaterGoal");

        user1 = new User("testName","test@email.com",1500,-1);
        user2 = new User("testName","test@email.com", 1500, -1111);

        assertEquals(0,user1.getWaterGoal());
        assertEquals(0,user2.getWaterGoal());

        System.out.println("Finished testInvalidWaterGoal");
    }

    @Test
    public void testValidSetCalorieGoal() {
        User user1;
        User user2;

        System.out.println("\nStarting testValidSetCalorieGoal");

        user1 = new User("testName","test@email.com");
        user2 = new User("testName","test@email.com",0,5);

        user1.setCalorieGoal(0);
        assertEquals(0,user1.getCalorieGoal());

        user2.setCalorieGoal(1500);
        assertEquals(1500,user2.getCalorieGoal());

        System.out.println("Finished testValidSetCalorieGoal");
    }

    @Test
    public void testInvalidSetCalorieGoal() {
        User user1;
        User user2;

        System.out.println("\nStarting testInvalidSetCalorieGoal");

        user1 = new User("testName","test@email.com");
        user2 = new User("testName","test@email.com",0,5);

        user1.setCalorieGoal(-1);
        assertEquals(0,user1.getCalorieGoal());
        user1.setCalorieGoal(-1000);
        assertEquals(0,user1.getCalorieGoal());
        user2.setCalorieGoal(-1);
        assertEquals(0,user2.getCalorieGoal());

        System.out.println("Finished testInvalidSetCalorieGoal");
    }

    @Test
    public void testValidSetWaterGoal() {
        User user1;
        User user2;

        System.out.println("\nStarting testValidSetWaterGoal");

        user1 = new User("testName","test@email.com");
        user2 = new User("testName","test@email.com",1500,0);

        user1.setWaterGoal(0);
        assertEquals(0,user1.getWaterGoal());
        user2.setWaterGoal(10);
        assertEquals(10,user2.getWaterGoal());

        System.out.println("Finished testValidSetWaterGoal");
    }

    @Test
    public void testInvalidSetWaterGoal() {
        User user1;
        User user2;

        System.out.println("\nStarting testInvalidSetWaterGoal");

        user1 = new User("testName","test@email.com");
        user2 = new User("testName","test@email.com",1500,0);

        user1.setWaterGoal(-1);
        assertEquals(0, user1.getWaterGoal());
        user1.setWaterGoal(-1000);
        assertEquals(0, user1.getWaterGoal());
        user2.setWaterGoal(-1);
        assertEquals(0, user2.getWaterGoal());

        System.out.println("Finished testInvalidSetWaterGoal");
    }

    @Test
    public void testValidChangeEmail() {
        User user1;
        User user2;
        User user3;

        System.out.println("\nStarting testValidChangeEmail");

        user1 = new User("test","test@email.com");
        user2 = new User("test","test@email.com",1500,10);
        user3 = new User("test","test@email.com",1500,10);

        user1.changeEmail("janesmith@gmail.com");
        assertEquals("janesmith@gmail.com", user1.getUserEmail());
        user2.changeEmail("jane.smith@gmail.com");
        assertEquals("jane.smith@gmail.com", user2.getUserEmail());
        user3.changeEmail("jane81@icloud.com");
        assertEquals("jane81@icloud.com", user3.getUserEmail());

        System.out.println("Finished testValidChangeEmail");
    }

    @Test
    public void testNullChangeEmail() {
        User user1;
        User user2;
        User user3;

        System.out.println("\nStarting testNullChangeEmail");

        user1 = new User("test","test@email.com");
        user2 = new User("test","test@email.com",1500,10);
        user3 = new User("test",null);

        user1.changeEmail(null);
        assertEquals("test@email.com", user1.getUserEmail());
        user2.changeEmail(null);
        assertEquals("test@email.com", user2.getUserEmail());
        user3.changeEmail(null);
        assertEquals("no-email", user3.getUserEmail());

        System.out.println("Finished testNullChangeEmail");
    }

    @Test
    public void testEmptyChangeEmail() {
        User user1;
        User user2;
        User user3;

        System.out.println("\nStarting testEmptyChangeEmail");

        user1 = new User("test","test@email.com");
        user2 = new User("test","test@email.com",1500,10);
        user3 = new User("test","");

        user1.changeEmail("");
        assertEquals("test@email.com", user1.getUserEmail());
        user2.changeEmail("");
        assertEquals("test@email.com", user2.getUserEmail());
        user3.changeEmail("");
        assertEquals("no-email", user3.getUserEmail());

        System.out.println("Finished testEmptyChangeEmail");
    }

}
