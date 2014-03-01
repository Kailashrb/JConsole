package org.jconsole;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by kailashbysani on 3/1/14.
 */
public class ShowCommandTest {

    static ShowCommand showCommand = new ShowCommand();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    static String myDir = System.getProperty("user.dir");
    static LSCommand LSCom = new LSCommand();

    @Before
    public void setup(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        JConsole jcon=JConsole.instance();
        jcon.setCurrentDir(myDir);
        LSCom.setConsole(jcon);
        try {
            //System.out.println("hi");
            String [] args1 = new String[0];
            LSCom.execute(args1);
        }catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        showCommand.setConsole(jcon);
    }


    @Test
    public void setShowCommand()
    {
        try {
            String [] args1=null;
            showCommand.execute(args1);
            String output=outContent.toString();
            Assert.assertTrue(output.contains("help"));
        } catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        outContent.reset();
    }



}
