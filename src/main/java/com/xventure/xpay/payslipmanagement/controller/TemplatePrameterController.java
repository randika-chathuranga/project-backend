package com.xventure.xpay.payslipmanagement.controller;

import com.xventure.xpay.payslipmanagement.modals.Template;
import com.xventure.xpay.payslipmanagement.modals.TemplateParameter;
import com.xventure.xpay.payslipmanagement.service.TemplateParameterService;
import com.xventure.xpay.payslipmanagement.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/templates-parameters")

public class TemplatePrameterController {
    @Autowired
    TemplateParameterService templateParameterService;

    @PostMapping
    public ResponseEntity<TemplateParameter> addTemplates(@RequestBody TemplateParameter templateParameter) {
      templateParameter= templateParameterService.addTemplateParameter(templateParameter);
        return new ResponseEntity<TemplateParameter>(templateParameter, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public List<TemplateParameter> findByTemplateId(@RequestParam("templateId") String templateId){
        return templateParameterService.findByTemplateId(templateId);
    }
    @GetMapping("/find-one")
    public Optional<TemplateParameter> findByParamId(@RequestParam("paramId") String paramId){
        return templateParameterService.findByParamId(paramId);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTemplateParameter(@PathVariable("id") String id) {
        templateParameterService.delete(id);
        return null;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<TemplateParameter> updateTemplateParameter(@PathVariable("id") String id, @RequestBody TemplateParameter templateParameter) {
        Optional<TemplateParameter> availableTemplateParameters = templateParameterService.findByParamId(id);

        if (availableTemplateParameters.isPresent()) {
            TemplateParameter templateParameter1 = availableTemplateParameters.get();
            templateParameter1.setParamId(templateParameter1.getParamId());
            templateParameter1.setParamKey(templateParameter1.getParamKey());
            templateParameter1.setValue(templateParameter1.getValue());
            return new ResponseEntity<>(templateParameterService.update(templateParameter), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
