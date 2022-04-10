package com.crud.app.product.web;

import com.crud.app.product.buisness.exception.ResourceNotFoundException;
import com.crud.app.product.buisness.service.ProductService;
import com.crud.app.product.data.entity.Product;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/app")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductService productService;

    @ExceptionHandler({ ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleException(ResourceNotFoundException ex) {
        return ex.getMessage();
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);

        if (product == null) {
            throw new ResourceNotFoundException("There is no product with following id: " + productId);
        }

        return product;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String creatProduct(@RequestBody Product product) {
        boolean result = productService.saveProduct(product);
        return result ? "SUCCESS" : "FAILED";
    }

    @RequestMapping(value = "/update/{productId}", method = RequestMethod.PATCH)
    public Product updateProduct(@PathVariable Long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }

    @RequestMapping(value = "/delete/{productId}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable Long productId) {
        boolean result = productService.deleteProduct(productId);
        return result ? "SUCCESS" : String.format("FAILED: No entity with id %d exists!", productId);
    }
}
