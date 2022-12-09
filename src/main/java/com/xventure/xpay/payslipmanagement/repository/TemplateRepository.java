package com.xventure.xpay.payslipmanagement.repository;

import com.xventure.xpay.payslipmanagement.modals.Template;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TemplateRepository extends MongoRepository<Template,String > {
    public Optional<Template> findByCode(String code);
}
