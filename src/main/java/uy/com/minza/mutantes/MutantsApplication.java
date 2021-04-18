package uy.com.minza.mutantes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(
        scanBasePackages = {"uy.com.minza.mutantes"}
)
@EnableScheduling
@EnableAsync
@EnableSwagger2
public class MutantsApplication {
    @Getter
    private static MutantsApplication instance = null;
    @Setter(value = AccessLevel.PRIVATE)
    private ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(MutantsApplication.class, args);
        if (instance == null) {
            instance = new MutantsApplication();
        }
        instance.setApplicationContext(applicationContext);
    }

    public <T> T getBean(Class<T> beanClass) {
        if (this.applicationContext == null) {
            return null;
        } else {
            return this.applicationContext.getBean(beanClass);
        }
    }
}
