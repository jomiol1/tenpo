package com.tenpo.api.controller;

import com.tenpo.api.entity.Historial;
import com.tenpo.api.service.AuditService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/audit")
public class AuditController {

    @Autowired
    AuditService auditService;

    @GetMapping("/{page}")
    public ResponseEntity<?> historial(@PathVariable Integer page) {
        Historial resultados = auditService.historial(page);
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }
}
