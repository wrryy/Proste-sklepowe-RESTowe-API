package pl.wrydz.sklep.service;

import org.springframework.stereotype.Service;
import pl.wrydz.sklep.entity.Product;
import pl.wrydz.sklep.repository.ProductRepo;

@Service
public class ProductService {

    private ProductRepo productRepo;

    public ProductService(ProductRepo itemRepo) {
        this.productRepo = itemRepo;
    }

    public Product getProduct(long productId){
        return productRepo.getOne(productId);
    }
}
