package com.journal.app;


import android.test.ActivityInstrumentationTestCase2;

import com.journal.app.activities.JNLoginActivity;

import org.junit.Before;
import org.junit.Test;


public class JNLoginActivityTest extends ActivityInstrumentationTestCase2<JNLoginActivity> {
    JNLoginActivity loginActivity = new JNLoginActivity();
    private static final String CLAZZ = JNLoginActivityTest.class.getName();

    public JNLoginActivityTest() {
        super(CLAZZ,JNLoginActivity.class);
    }







    @Before
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(false);
        loginActivity = getActivity();
    }

    @Test
    public void testMissingPasswordField() {




        try {


            loginActivity.isPasswordValid("74");

        } catch (Throwable error) {
           assertEquals(error.getMessage(), "Invalid Email or Password");
          }


    }


    @Test
    public void testMissingUsernameField() throws Exception  {




        try {

            loginActivity.isEmailValid("");
        } catch (Throwable error) {
            System.out.println("Error message" +"  " + error.getMessage());
            assertEquals(error.getMessage(), "Invalid Email or Password");

        }

    }





}


