package com.xventure.xpay.employeemanagement.repository;

import com.xventure.xpay.employeemanagement.models.Employee;
import com.xventure.xpay.employeemanagement.models.EmployeeRequest;
import com.xventure.xpay.employeemanagement.models.Leave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


public interface LeaveRepository extends MongoRepository<Leave,String> {


    List<Leave> findByLId(String lId);

}
