package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.bd.tpfinal.model.Product;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    public List<Product> findByNameIgnoreCaseContaining(String name);
    // el parametro nativeQuery es is usas sql nativo sino es JPL y el * no anda asi.
    @Query(value = "SELECT * FROM Product p WHERE p.supplier_id =:supplier_id", nativeQuery = true)
    public List<Product> findProductsBySupplierId(@Param("supplier_id") Long supplier_id);
}