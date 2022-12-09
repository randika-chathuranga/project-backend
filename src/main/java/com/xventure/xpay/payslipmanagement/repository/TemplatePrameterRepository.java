package com.xventure.xpay.payslipmanagement.repository;

import com.xventure.xpay.payslipmanagement.modals.TemplateParameter;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TemplatePrameterRepository extends MongoRepository<TemplateParameter,String> {
    public List<TemplateParameter> findByTemplateId(String templateId);
    Optional<TemplateParameter> findByParamId(String paramId);

}
