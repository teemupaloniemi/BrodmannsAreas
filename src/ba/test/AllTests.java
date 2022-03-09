package ba.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Teemu
 * @version 9.3.2022
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AreasTest.class, AreaTest.class, BaTest.class,
        FunctionTest.class, FunctionsTest.class,  LocationsTest.class, LocationTest.class })
public class AllTests {
    //
}
