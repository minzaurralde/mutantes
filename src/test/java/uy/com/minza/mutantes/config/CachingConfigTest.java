package uy.com.minza.mutantes.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CachingConfigTest {

    @Autowired
    private CachingConfig cachingConfig;

    @Test
    public void cacheManagerTest() {
        Assertions.assertAll(
                () -> Arrays.asList("stats", "dnas", "results").containsAll(cachingConfig.cacheManager().getCacheNames()),
                () -> cachingConfig.cacheManager().getCacheNames().containsAll(Arrays.asList("stats", "dnas", "results"))
        );
    }
}