package com.tenpo.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;


@Data
public class RegistryDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
