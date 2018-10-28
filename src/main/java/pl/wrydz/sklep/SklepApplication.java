package pl.wrydz.sklep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "pl.wrydz.sklep")
@EnableJpaRepositories
public class SklepApplication {

    public static void main(String[] args) { SpringApplication.run(SklepApplication.class, args); }

//    @Bean()
//    MainController mainController(){
//        return new MainController();
//    }

}
