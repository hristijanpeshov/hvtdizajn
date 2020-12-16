package hvt.proekt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ProektApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProektApplication.class, args);
    }

}
