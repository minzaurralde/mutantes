package uy.com.minza.mutantes.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion del caché
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 */
@Configuration
@EnableCaching
public class CachingConfig {

    /**
     * Crea el caché manager
     *
     * @return  Un objeto cache manager con los nombres de los caches
     */
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("stats", "dnas", "results");
    }
}
