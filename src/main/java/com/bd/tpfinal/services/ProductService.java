package com.bd.tpfinal.services;

import com.bd.tpfinal.DTOs.ProductDTO;
import com.bd.tpfinal.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProductsAndTypeBySupplierId(long supplier_id);
    /*
    **   Interface que define los metodos que debe implementar un servicio
     */

}
