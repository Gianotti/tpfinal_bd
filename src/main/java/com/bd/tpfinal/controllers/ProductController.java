package com.bd.tpfinal.controllers;

import com.bd.tpfinal.DTOs.ProductDTO;
import com.bd.tpfinal.model.Product;
import com.bd.tpfinal.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{supplier_id}")
    public List<Product> getProductsAndTypeBySupplier(@PathVariable long supplier_id) {
        return productService.getProductsAndTypeBySupplierId(supplier_id);
    }
}
