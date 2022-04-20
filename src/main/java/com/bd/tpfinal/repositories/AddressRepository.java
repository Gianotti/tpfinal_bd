package com.bd.tpfinal.repositories;

import com.bd.tpfinal.model.Address;
import com.bd.tpfinal.model.SupplierType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {
    public Optional<Address> findAddressById(Long anId);
}
