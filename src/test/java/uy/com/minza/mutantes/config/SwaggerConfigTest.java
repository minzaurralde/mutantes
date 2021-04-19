package uy.com.minza.mutantes.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.servlet.ServletContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureWebMvc
public class SwaggerConfigTest {

    @Autowired
    private SwaggerConfig swaggerConfig;
    @Autowired
    private ServletContext servletContext;

    @Test
    public void api() {
        Assertions.assertNotNull(swaggerConfig.api(servletContext));
    }
}