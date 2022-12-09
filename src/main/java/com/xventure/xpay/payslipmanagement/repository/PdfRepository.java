package com.xventure.xpay.payslipmanagement.repository;

import com.xventure.xpay.payslipmanagement.modals.PdfRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PdfRepository extends MongoRepository<PdfRequest,String> {
    Page<PdfRequest> findAll(Pageable pageable);
}
