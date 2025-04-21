package com.me.Repositories;

import com.me.Models.Entities.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Override
    ArrayList<CustomerEntity> findAll();
    Optional<CustomerEntity> findByEmail(String email);
}
