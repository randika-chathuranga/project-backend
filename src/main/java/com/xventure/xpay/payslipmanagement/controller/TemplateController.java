package com.xventure.xpay.payslipmanagement.controller;

import com.xventure.xpay.payslipmanagement.modals.Template;
import com.xventure.xpay.payslipmanagement.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/templates")
public class TemplateController {
    @Autowired
    TemplateService templateService;

    @PostMapping
    public ResponseEntity<Template> addTemplates(@RequestBody Template template) {
       template= templateService.addTemplate(template);
        return new ResponseEntity<Template>(template, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Template>> getAllTemplates(){
       return ResponseEntity.ok(templateService.getAllTemplates());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteTemplate(@PathVariable("id") String id) {
        templateService.delete(id);
        return null;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Template> updateTemplate(@PathVariable("id") String id, @RequestBody Template template) {
        Optional<Template> availableTemplate = templateService.findById(id);

        if (availableTemplate.isPresent()) {
            Template template1 = availableTemplate.get();
            template1.setName(template.getName());
            template1.setCode(template.getCode());
            template1.setFileName(template.getFileName());
            return new ResponseEntity<>(templateService.update(template), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
