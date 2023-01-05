package com.tenpo.api.service;

import com.google.gson.Gson;
import com.tenpo.api.entity.AuditEntity;
import com.tenpo.api.entity.Historial;
import com.tenpo.api.repository.AuditRequestRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AuditService {

    private static final Integer SIZE = 5;
    Gson gson = new Gson();

    @Autowired
    AuditRequestRepository auditoriaRequestRepository;

    @Async
    public void audit(Object body, HttpServletResponse response, HttpServletRequest request) {

        Integer status = response.getStatus();
        if (esAuditoriaRequest(status)) {
            AuditEntity auditoria = new AuditEntity();
            auditoria.setUri(request.getRequestURI());
            auditoria.setStatus(status);
            auditoria.setResponse(getBody(body));
            auditoria.setFechaCreacion(new Date());
            auditoriaRequestRepository.save(auditoria);
        }
    }

    public Historial historial(Integer page) {
        Pageable pageable = PageRequest.of(page, SIZE);
        Page<AuditEntity> pages = auditoriaRequestRepository.findAllByOrderByIdDesc(pageable);
        valid(pages.getContent());
        return new Historial(pages.getTotalPages(), pages.getTotalElements(), pages.getContent().size(), pages.getContent());
    }

    private void valid(List<AuditEntity> auditorias) {
        if (auditorias.isEmpty()) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND, "No existen resultados para la búsqueda");
        }
    }

    private String getBody(Object body) {
        return gson.toJson(body);
    }

    private boolean esAuditoriaRequest(Integer status) {
        return status.equals(HttpStatus.OK.value()) ||
                status.equals(HttpStatus.CREATED.value()) ||
                status.equals(HttpStatus.NO_CONTENT.value());
    }
}
