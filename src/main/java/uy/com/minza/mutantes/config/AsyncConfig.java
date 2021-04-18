package uy.com.minza.mutantes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * Configuración de las capacidades asincrónicas de la aplicación
 *
 * @author Martin Inzaurralde - eminzaurralde@gmail.com
 */
@Configuration
@EnableAsync
public class AsyncConfig {

    /**
     * Crea el ejecutor para el pool de hilos que manejan las estadísticas
     *
     * @return Un objeto ThreadPoolTaskExecutor configurado para las estadísticas
     */
    @Bean(name = "statsExecutor")
    public Executor statsExecutor(
            @Value("${mutant.threads.stats.maxPoolSize:10}") int maxPoolSize,
            @Value("${mutant.threads.stats.corePoolSize:5}") int corePoolSize,
            @Value("${mutant.threads.stats.queueCapacity:1000000}") int queueCapacity,
            @Value("${mutant.threads.stats.keepAliveSeconds:60}") int keepAliveSeconds
    ) {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        return threadPoolTaskExecutor;
    }

    /**
     * Crea el ejecutor para el pool de hilos donde ejecuta el almacenamiento
     *
     * @return Un objeto ThreadPoolTaskExecutor configurado para el almacenamiento
     */
    @Bean(name = "repoExecutor")
    public Executor repoExecutor(
            @Value("${mutant.threads.repository.maxPoolSize:10}") int maxPoolSize,
            @Value("${mutant.threads.repository.corePoolSize:5}") int corePoolSize,
            @Value("${mutant.threads.repository.queueCapacity:1000000}") int queueCapacity,
            @Value("${mutant.threads.repository.keepAliveSeconds:60}") int keepAliveSeconds
    ) {
        final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        return threadPoolTaskExecutor;
    }
}
