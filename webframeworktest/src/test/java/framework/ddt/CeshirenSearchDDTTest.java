package framework.ddt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

public class CeshirenSearchDDTTest {

    static WebDriver webDriver;


    @ParameterizedTest
    @MethodSource("data")
    public void runTestCase(TestCase testCase){

        testCase.run();

    }

    static Stream<TestCase> data() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase testCase = mapper.readValue(
                new File("src/test/java/framework/CeshirenSearchTest.yaml"),
                TestCase.class);
        return Stream.of(testCase);

    }

}
