package com.bd.tpfinal.services;

import com.bd.tpfinal.DTOs.ProductDTO;
import com.bd.tpfinal.model.Product;
import com.bd.tpfinal.repositories.ProductRepository;
import com.bd.tpfinal.repositories.ProductTypeRepository;
import com.bd.tpfinal.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductsAndTypeBySupplierId(long supplier_id) {
        return productRepository.findProductsBySupplierId(supplier_id);
    }
}
