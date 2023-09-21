package com.vodafone.demo.controller;

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
public class flnController {
    @Autowired
    @Qualifier("fln")
    private BaseService service;
    @GetMapping("/fln/{fln}")
    public ResponseEntity<FlnDetails> getFlnDetails(@NotNull @Pattern(
            regexp = "^((\\+441\\d{8,9})|(\\+442\\d{8,9})|(441\\d{8,9})|(442\\d{8,9})|(01\\d{8,9})|(02\\d{8,9}))$",
            message = "Fixed line number is not valid - expected pattern: ^((\\+441\\d{8,9})|(\\+442\\d{8,9})|(441\\d{8,9})|(442\\d{8,9})|(01\\d{8,9})|(02\\d{8,9}))$ "
    )@PathVariable("fln") String fln){
        return new ResponseEntity<FlnDetails>((FlnDetails) service.getDetails(fln), HttpStatus.OK);
    }
}
