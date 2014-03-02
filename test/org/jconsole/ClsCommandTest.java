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
public class ClsCommandTest {
    static CLSCommand clsCommand = new CLSCommand();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    static String curDir = System.getProperty("user.dir");
    static LSCommand LSCom = new LSCommand();
    private final String newLinePattern = System.getProperty("line.separator");
    public int lineCount = 0;

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        JConsole jcon = JConsole.instance();
        jcon.setCurrentDir(curDir);
        jcon.processLine("ls");
        //jcon.run();
        LSCom.setConsole(jcon);
        try {
            System.out.println("hwllo");
            String[] args1 = new String[0];
            String sampleFile = "Jconsole.xml";
            LSCom.execute(args1);
            String output = outContent.toString();
            String[] lineSplit = output.split(newLinePattern);
            lineCount = lineSplit.length;

        } catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        clsCommand.setConsole(jcon);
    }


    @Test
    public void setHistoryCommand() {
        try {
            String[] args1 = {"1"};
            clsCommand.execute(args1);
            String output = outContent.toString();
            String[] lineSplit = output.split(newLinePattern);
            int newlineCount = lineSplit.length;
            Assert.assertFalse(newlineCount - lineCount == 50);
        } catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        outContent.reset();
    }
}
