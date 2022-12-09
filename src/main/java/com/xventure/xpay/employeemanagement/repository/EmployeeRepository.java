package com.xventure.xpay.employeemanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.xventure.xpay.employeemanagement.models.Employee;


public interface EmployeeRepository extends MongoRepository<Employee, String> {
    public  Optional<Employee> findByEmail(String email);
    public Boolean existsByEmail(String email);



    Employee findByToken(String token);



    Page<Employee>findByStatus(String status, Pageable pageable);
}
