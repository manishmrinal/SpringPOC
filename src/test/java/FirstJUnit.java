
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class FirstJUnit {

  static Logger log = LoggerFactory.getLogger(FirstJUnit.class);

    private List<String> in = new ArrayList<>(Arrays.asList("Hello", "Yes", "No"));
    private List<String> out = new ArrayList<>(Arrays.asList("Cześć", "Tak", "Nie"));

    @BeforeAll
    static void setup() {
        log.info("Before All executed");
    }

    @BeforeEach
    public void beforeEach() {
        log.info("Before each executed");
    }

    @AfterEach
    public void afterEach() {
        log.info("after each method");
    }

    @DisplayName("First Test")
    @Test
    public void firstTest() {
        log.info("First Test");
    }

    @DisplayName("Second Test")
    @Test
    public void secondTest() {
        log.info("Second Test");
    }

    @Test
     void  lambdaExpressions() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Assertions.assertTrue(numbers.stream()
                .mapToInt(Integer::intValue)
                .sum() > 4, () -> "Hello");
    }

    @Test
    void groupAssertions() {
        int[] numbers = {0, 1, 2, 3, 4};
        Assertions.assertAll("numbers",
                () -> assertEquals(numbers[0], 0),
                () -> assertEquals(numbers[3], 3),
                () -> assertEquals(numbers[4], 4)
        );
    }

    @TestFactory
    Stream<DynamicTest> translateDynamicTestsFromStream() {
        return in.stream()
                .map(word ->
                        DynamicTest.dynamicTest("Test translate " + word, () -> {
                            int id = in.indexOf(word);
                            assertEquals(out.get(id), translate(word));
                        })
                );
    }

    private String translate(String word) {
        if ("Hello".equalsIgnoreCase(word)) {
            return "Cześć";
        } else if ("Yes".equalsIgnoreCase(word)) {
            return "Tak";
        } else if ("No".equalsIgnoreCase(word)) {
            return "Nie";
        }
        return "Error";
    }




}
