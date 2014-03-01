package org.jconsole;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.jconsole.commands.pingCommand;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.fail;

/**
 * Created by kailashbysani on 3/1/14.
 */
public class PingCommandTest {
    static pingCommand pingCommand = new pingCommand();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setup() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        JConsole jcon = JConsole.instance();
        pingCommand.setConsole(jcon);
    }


    @Test
    public void setHistoryCommand() {
        try {
            String[] args1 = {"localhost"};
            pingCommand.execute(args1);
            String output = outContent.toString();
            Assert.assertTrue(output.contains("127.0.0.1"));
        } catch (CommandFailedException e) {
            e.printStackTrace();
            fail("Exception was thrown");
        }
        outContent.reset();
    }
}
