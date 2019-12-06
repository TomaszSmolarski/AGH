package java3.testy;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SuiteDisplayName("JUnit Platform Suite Demo")
@SelectPackages("Testy")
@SelectClasses({
        sol2Test.class,SolTest.class,sortTest.class,subTest.class,matrixTest.class,matrixIteratorTest.class})
public class TestSuite {
}
