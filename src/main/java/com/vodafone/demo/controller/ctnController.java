package com.vodafone.demo.controller;

import com.vodafone.demo.model.CtnDetails;
import com.vodafone.demo.model.FlnDetails;
import com.vodafone.demo.service.BaseService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Validated
public class ctnController {
    @Autowired
    @Qualifier("ctn")
    private BaseService service;
    @GetMapping("/msisdn/{msisdn}")
    public ResponseEntity<CtnDetails> getCtnDetails(@NotNull @Pattern(
            regexp = "^((\\+447\\d{9})|(447\\d{9})|(07\\d{9}))$",
            message = "Msisdn is not valid - expected pattern: ^((\\+447\\d{9})|(447\\d{9})|(07\\d{9}))$ "
    )@PathVariable("msisdn") String ctn){
        return new ResponseEntity<CtnDetails>((CtnDetails) service.getDetails(ctn),HttpStatus.OK);
    }

}
