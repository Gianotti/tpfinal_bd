package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Supplier;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SupplierRepository extends MongoRepository<Supplier, String> {
    List<Supplier> findByQualificationOfUsersGreaterThan(float qualification);


}