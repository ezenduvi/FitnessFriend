package com.example.myfitnessfriend;

import com.example.myfitnessfriend.Persistence.DatabaseTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PersistenceTests {

    public static TestSuite suite;

    public static Test suite()
    {
        suite = new TestSuite("Persistence tests");
        suite.addTestSuite(DatabaseTest.class);
        return suite;
    }
}
