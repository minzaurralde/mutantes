package uy.com.minza.mutantes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MutantsApplication.class)
public class MutantsApplicationTest {
    @Test
    public void contextLoads() {
    }
}