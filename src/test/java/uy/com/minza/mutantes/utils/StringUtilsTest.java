package uy.com.minza.mutantes.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class StringUtilsTest {

    @Autowired
    private StringUtils stringUtils;

    @Test
    public void containsOnlyOKTest() {
        Assertions.assertTrue(this.stringUtils.containsOnly("AAAABBBBGGGTTT", 'A', 'B', 'G', 'T'));
    }

    @Test
    public void containsOnlyEmptyStringTest() {
        Assertions.assertTrue(this.stringUtils.containsOnly("", 'A', 'B', 'G', 'T'));
    }

    @Test
    public void containsOnlyEmptyCharArrayTest() {
        Assertions.assertFalse(this.stringUtils.containsOnly("AAAABBBBGGGTTT"));
    }

    @Test
    public void containsOnlyNotOKTest() {
        Assertions.assertFalse(this.stringUtils.containsOnly("AAAABBBBGGGTTT", 'A', 'B', 'G'));
    }
}