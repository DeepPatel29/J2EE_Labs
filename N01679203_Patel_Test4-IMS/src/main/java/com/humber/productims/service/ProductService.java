package com.humber.productims.service;

import com.humber.productims.entity.Product;
import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProductById(Long id);

    Product saveProduct(Product product);

    void deleteProduct(Long id);
}