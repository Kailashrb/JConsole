package org.jconsole;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.lang.System;

import java.security.Permission;

/**
 * Created by kailashbysani on 3/1/14.
 */
public class ExitCommandTest {
    QuitCommand Qcmd = new QuitCommand();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setSecurityManager(new TestSecurityManager());
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        JConsole jcon = JConsole.instance();
        Qcmd.setConsole(jcon);
    }


    @Test
    public void systemExitWithSelectedStatusCode() {
        try {
            String[] args1 = new String[0];
            Qcmd.execute(args1);
            System.out.println("afsfsafsaf");
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
