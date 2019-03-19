package com.mf;

import java.util.*;
import java.io.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.utils.*;
import com.hp.lft.report.*;
import com.hp.lft.verifications.*;

import unittesting.*;

public class LeanFtTest extends UnitTestClassBase {

    public LeanFtTest() {
        //Change this constructor to private if you supply your own public constructor
    }

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAlmUtilsExample() throws GeneralLeanFtException, ReportException, IOException {
        // This test check if the test is running from ALM, and if it is,
        // it will write to the LeanFT report all the properties that AlmUtils support.
        AlmRunInfo almRunInfo = AlmUtils.getAlmRunInfo();
        if (almRunInfo != null){
            // AlmUtils.getAlmRunInfo() returned object, the test is running from ALMreturn;
            // Create a string builder, and add the properties of the test running
            StringBuilder sb = new StringBuilder();
            sb.append("ALMUtils test info proeprties: <BR>");
            sb.append(String.format("ServerUrl: '%s' <BR>", almRunInfo.getServerUrl()));
            sb.append(String.format("TesterName: '%s' <BR>", almRunInfo.getTesterName()));
            sb.append(String.format("TestId: '%d' <BR>", almRunInfo.getTestId()));
            sb.append(String.format("TestName: '%s' <BR>", almRunInfo.getTestName()));
            sb.append(String.format("TestSetId: '%d' <BR>", almRunInfo.getTestSetId()));
            sb.append(String.format("TestSetName: '%s' <BR>", almRunInfo.getTestSetName()));
            sb.append(String.format("RunId: '%d' <BR>", almRunInfo.getRunId()));
            sb.append(String.format("RunName: '%s' <BR>", almRunInfo.getRunName()));
            sb.append(String.format("TestInstanceId: '%d' <BR>", almRunInfo.getTestInstanceId()));
            String stringToReport = sb.toString();
            Reporter.reportEvent("AlmUtils.getAlmRunInfo() Properties Check", stringToReport);

        }

        try {
            Map<String, Object> testParams = almRunInfo.getParameters();

            if (testParams == null) {
                Reporter.reportEvent("AlmUtils.getAlmRunInfo() Parameters Check",
                        "Test is not running from ALM, therefore no parameters in the test");
            } else {
                String paramString = "";
                for (Map.Entry<String, Object> testParam : testParams.entrySet()) {
                    paramString += String.format("Parameter name: '%s', parameter value: '%s' <BR>", testParam.getKey(), testParam.getValue().toString());
                }
                Reporter.reportEvent("ALMUtils.getAlmRunInfo Parameters Check", paramString);
            }
        } catch (NullPointerException e) {
            Reporter.reportEvent("Exception in AlmUtils.getAlmRunInfo() Parameters Check",
                    "Test is not running from ALM, therefore no parameters in the test");
        }
    }
}