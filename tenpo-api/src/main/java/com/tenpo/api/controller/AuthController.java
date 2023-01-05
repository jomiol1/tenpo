package com.tenpo.api.controller;

import com.tenpo.api.dto.UserDTO;
import com.tenpo.api.service.AuthService;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO loginDTO) {
        String token = authService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/registry")
    public ResponseEntity<Void> registry(@RequestBody @Valid UserDTO registryDTO) {
        authService.registry(registryDTO.getUsername(), registryDTO.getPassword());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
