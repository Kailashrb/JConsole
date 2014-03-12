package org.jconsole;

import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LSCommandTest.class,
        PWDCommandTest.class,
        HelpCommandTest.class,
        CDCommandTest.class,
        CPCommandTest.class,
        PingCommandTest.class,
        MKDIRCommandTest.class,
        RMDIRCommandTest.class,
        ClsCommandTest.class,

        QuitCommandTest.class,

        ExitCommandTest.class
        //ShowCommandTest.class,

})

public class TestSuite {

}