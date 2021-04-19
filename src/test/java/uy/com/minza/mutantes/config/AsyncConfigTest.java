package uy.com.minza.mutantes.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AsyncConfigTest {

    @Autowired
    private AsyncConfig asyncConfig;

    @Test
    public void statsExecutor() {
        Assertions.assertNotNull(asyncConfig.statsExecutor(100, 5, 5000, 60));
    }

    @Test
    public void repoExecutor() {
        Assertions.assertNotNull(asyncConfig.repoExecutor(100, 5, 5000, 60));
    }
}