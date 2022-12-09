package com.xventure.xpay.payslipmanagement.repository;

import com.xventure.xpay.payslipmanagement.modals.Payslip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PayslipRepository extends MongoRepository<Payslip, String> {
    //    public List<Payslip> findByPayslipId(String Id);
    Optional<Payslip> findById(String id);
//    Page<Payslip> findByEnoContainingIgnoreCase(String eno,String month,Pageable pageable);
Page<Payslip> findByEnoContainingIgnoreCase(String eno,Pageable pageable);

}
