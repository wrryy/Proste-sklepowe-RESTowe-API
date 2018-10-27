package pl.wrydz.sklep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrydz.sklep.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

}
