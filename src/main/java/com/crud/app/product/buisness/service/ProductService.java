package com.crud.app.product.buisness.service;

import com.crud.app.product.buisness.exception.ResourceNotFoundException;
import com.crud.app.product.data.entity.Product;
import com.crud.app.product.data.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        Iterable<Product> products = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        products.forEach(productList::add);

        return productList;
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    public boolean saveProduct(Product product) {
        boolean result = true;

        Product savedProduct = productRepository.save(product);

        if (!product.equals(savedProduct)) {
            result = false;
        }

        return result;
    }

    public Product updateProduct(Long productId, Product product) {
        Product modifyProduct = productRepository.findById(productId).orElse(null);

        if (modifyProduct == null) {
            throw new ResourceNotFoundException("There is no product with following id: " + productId);
        }
        modifyProduct.setProductPrice(product.getProductPrice());
        modifyProduct.setProductName(product.getProductName());
        modifyProduct.setProductUrl(product.getProductUrl());

        productRepository.save(modifyProduct);

        return modifyProduct;
    }

    public boolean deleteProduct(Long productId) {
        boolean result = true;

        try {
            productRepository.deleteById(productId);
        } catch (EmptyResultDataAccessException e) {
            result = false;
        }
        return result;
    }
}
