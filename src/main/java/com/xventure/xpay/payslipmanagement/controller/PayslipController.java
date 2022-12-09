package com.xventure.xpay.payslipmanagement.controller;

import com.xventure.xpay.payslipmanagement.modals.Payslip;
import com.xventure.xpay.payslipmanagement.modals.Template;
import com.xventure.xpay.payslipmanagement.service.PayslipService;
import com.xventure.xpay.payslipmanagement.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/payslips")
public class PayslipController {
    @Autowired
    PayslipService payslipService;
    @Autowired
    TemplateService templateService;

    @PostMapping
    public ResponseEntity<Payslip> addTemplates(@RequestBody Payslip payslip) {
        payslip = payslipService.addPayslipDetails(payslip);
        return new ResponseEntity<Payslip>(payslip, HttpStatus.CREATED);
    }

//    @GetMapping
//    public ResponseEntity<Page<Payslip>> getAllPayslips(@RequestParam ("eno") String eno,@RequestParam("month") String month, Pageable pageable) {
//        return ResponseEntity.ok(payslipService.getAllPayslips(eno,month, pageable));
//    }
@GetMapping
public ResponseEntity<Page<Payslip>> getAllPayslips(@RequestParam ("eno") String eno, Pageable pageable) {
    return ResponseEntity.ok(payslipService.getAllPayslips(eno, pageable));
}



    @GetMapping("/find")
    public Optional<Payslip> findByPayslipId(@RequestParam("id") String id) {
        return payslipService.findByPayslipId(id);
    }

    //print payslip by id
    @GetMapping("/download")
    public Optional<Payslip> findByPayslipId(@RequestParam("id") String id, HttpServletResponse response) throws Exception {
        Optional<Payslip> payslip = Optional.of(payslipService.findByPayslipId(id).get());
        Optional<Template> template = payslipService.findTemplateByCode("ID456");
        ;
        if (template.isPresent()) {
            Template existingTemplate = template.get();
            String fileName = existingTemplate.getFileName();
            Map<String, Object> params = new HashMap<>();
            Optional<Payslip> payslip1 = payslipService.findByPayslipId(id);
            String allowance= String.valueOf(payslip.get().getAllowance());
            String eno= String.valueOf(payslip.get().getEno());
            String ename= String.valueOf(payslip.get().getEname());
            String tax= String.valueOf( payslip.get().getTax());
            String netSalary= String.valueOf(payslip.get().getNetSalary());
            String basicSalary= String.valueOf(payslip.get().getBasicSalary());
            String overTimeAmount= String.valueOf(payslip.get().getOverTimeAmount());
            String month= String.valueOf(payslip.get().getMonth());
            params.put("allowance",allowance);
            params.put("month",month);
            params.put("eno",eno);
            params.put("ename",ename);
            params.put("basicSalary",basicSalary);
            params.put("tax",tax);
            params.put("overTime",overTimeAmount);
            params.put("netSalary",netSalary);
            payslipService.downloadPDF(fileName, params, response);
        }
        return null;
    }
}
