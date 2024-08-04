package com.example.myfitnessfriend;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.example.myfitnessfriend.Integration.IntegrationTests;

public class RunIntegrationTests {

    public static TestSuite suite;

    public static Test suite() {
        suite = new TestSuite("Integration tests");
        suite.addTest(IntegrationTests.suite());
        return suite;
    }
}
