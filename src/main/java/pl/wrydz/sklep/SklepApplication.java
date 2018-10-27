package pl.wrydz.sklep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import javax.persistence.EntityManagerFactory;

@SpringBootApplication(scanBasePackages = "pl.wrydz.sklep")
@EnableJpaRepositories
@EnableCaching
public class SklepApplication {

    public static void main(String[] args) { SpringApplication.run(SklepApplication.class, args); }

//    @Bean()
//    MainController mainController(){
//        return new MainController();
//    }

}
