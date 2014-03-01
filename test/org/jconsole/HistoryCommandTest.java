package org.jconsole;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;


public class HistoryCommandTest {

    static HistoryCommand historyCommand = new HistoryCommand();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    static String curDir = System.getProperty("user.dir");
    static CPCommand CpCom = new CPCommand();

    @Before
    public void setup(){
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        JConsole jcon=JConsole.instance();
        jcon.setCurrentDir(curDir);
        CpCom.setConsole(jcon);
        try {
            String srcDir = curDir + "/testResource/testDoc.txt";
            String [] args1 = {srcDir, "."};
            CpCom.execute(args1);
            String destfile = args1[0].substring(args1[0].lastIndexOf("/"));
            String destPath = curDir + destfile;
            File f = new File(destPath);
            if(!(f.exists())) {
                fail("File not found");
            }
        }catch (CommandFailedException e) {
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
            Assert.assertTrue(output.contains("help"));
        } catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        outContent.reset();
    }
}
