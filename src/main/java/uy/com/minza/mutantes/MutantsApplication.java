package uy.com.minza.mutantes;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import uy.com.minza.mutantes.service.StatsService;

@SpringBootApplication(scanBasePackages = {"uy.com.minza.mutantes"})
public class MutantsApplication {
    @Getter
    private static MutantsApplication instance = new MutantsApplication();
    @Setter(value = AccessLevel.PACKAGE)
    private ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        final ApplicationContext applicationContext = SpringApplication.run(MutantsApplication.class, args);
        init(applicationContext);
    }

    public static void init(final ApplicationContext applicationContext) {
        getInstance().setApplicationContext(applicationContext);
        if (getInstance().getBean(StatsService.class) != null) {
            getInstance().getBean(StatsService.class).init();
        }
    }

    public <T> T getBean(Class<T> beanClass) {
        if (this.applicationContext == null) {
            return null;
        } else {
            return this.applicationContext.getBean(beanClass);
        }
    }
}
