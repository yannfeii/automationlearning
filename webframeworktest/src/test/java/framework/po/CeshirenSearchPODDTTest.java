package framework.po;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.stream.Stream;

public class CeshirenSearchPODDTTest {

    static WebDriver webDriver;


    @ParameterizedTest
    @MethodSource("data")
    public void runTestCase(TestCasePO testCasePO){

        testCasePO.run();

    }

    static Stream<TestCasePO> data() throws IOException {

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCasePO testCase = mapper.readValue(
                new File("src/test/java/framework/CeshirenSearchTPOest.yaml"),
                TestCasePO.class);
        return Stream.of(testCase);

    }

}
