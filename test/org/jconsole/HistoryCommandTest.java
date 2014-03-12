package org.jconsole;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import static org.junit.Assert.assertTrue;
import java.io.PrintStream;


public class HistoryCommandTest {
    static HistoryCommand historyCommand = new HistoryCommand();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    static String curDir = System.getProperty("user.dir");
    static LSCommand LSCom = new LSCommand();

    @Before
    public void setup(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        JConsole jcon=JConsole.instance();
        jcon.setCurrentDir(curDir);
        jcon.processLine("ls");
        //jcon.run();
        LSCom.setConsole(jcon);
        try {
           // System.out.println("hwllo");
            String [] args1 = new String[0];
            String sampleFile = "Jconsole.xml";
            LSCom.execute(args1);
            String output=outContent.toString();
            System.out.println("the out put of ls command is:"+output);
            assertTrue(output.contains(sampleFile));
        }
        catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        historyCommand.setConsole(jcon);
    }


    @Test
    public void setHistoryCommand()
    {
        try {
            String [] args1={"1"};
            historyCommand.execute(args1);
            String output=outContent.toString();
            Assert.assertTrue(output.contains("ls"));
        } catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        outContent.reset();
    }
}
