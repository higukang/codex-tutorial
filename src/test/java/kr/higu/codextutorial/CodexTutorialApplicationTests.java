package kr.higu.codextutorial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CodexTutorialApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void readmeContainsOnlyExpectedText() throws IOException {
        String readme = Files.readString(Path.of("README.md"));

        assertEquals("""
                - Java 21
                - Gradle
                - Spring Boot 4.0.4
                - Spring Web MVC
                - Lombok
                - JUnit 5
                """.trim(), readme.trim());
    }

}
