package pl.wrydz.sklep.service;

import org.springframework.stereotype.Service;
import pl.wrydz.sklep.entity.Product;
import pl.wrydz.sklep.repository.ProductRepo;

import java.util.List;

@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo itemRepo) {
        this.productRepo = itemRepo;

    }

    Product getProduct(long productId){
        return productRepo.getOne(productId);
    }

    /**
     * Workaround for importing data.sql
     */
    public void add(){
        for (long i = 1; i < 10; i++) {
            Product prod = new Product();
            prod.setId(i);
            prod.setName("Prod"+i);
            prod.setPrice(10*i);
            productRepo.save(prod);
        }
        productRepo.flush();
    }
    public List<Product> findAll(){
        return productRepo.findAll();
    }
}
