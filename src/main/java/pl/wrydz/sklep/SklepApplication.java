package pl.wrydz.sklep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pl.wrydz.sklep.discount.ItemAmountDiscount;

@SpringBootApplication(scanBasePackages = "pl.wrydz.sklep")
@EnableJpaRepositories
@EnableTransactionManagement
public class SklepApplication {

    public static void main(String[] args) { SpringApplication.run(SklepApplication.class, args); }

    @Bean()
    ItemAmountDiscount itemAmountDiscount(){
        return new ItemAmountDiscount(2L, 2, 15);
    }


}
