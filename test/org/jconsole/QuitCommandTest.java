package org.jconsole;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.lang.System;

import java.security.Permission;

import java.security.Permission;

/**
 * Created by kailashbysani on 3/1/14.
 */
public class QuitCommandTest {

    QuitCommand quitCommand = new QuitCommand();

    @Before
    public void setup() {
        System.setSecurityManager(new TestSecurityManager());
        JConsole jcon = JConsole.instance();
        quitCommand.setConsole(jcon);
    }


    @Test
    public void systemExitWithSelectedStatusCode() {
        try {
            String[] args1 = new String[0];
            quitCommand.execute(args1);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("The erros code is :" + e.getMessage());
            Assert.assertTrue(e.getMessage().contains("System.exit(0) has been called"));
        }

    }

    @After
    public void tearDown() throws Exception {
        System.setSecurityManager(null);
        //super.tearDown();
    }

    private class TestSecurityManager extends SecurityManager {
        @Override
        public void checkExit(final int status) {
            throw new SecurityException("System.exit(" + status + ") has been called");
        }

        @Override
        public void checkPermission(final Permission perm) {
            // nothing forbidden
        }

        @Override
        public void checkPermission(final Permission perm, final Object context) {
            // nothing forbidden
        }
    }
}
