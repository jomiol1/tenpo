package com.tenpo.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tenpo.api.entity.AuditEntity;


@Repository
public interface AuditRequestRepository extends CrudRepository<AuditEntity, Integer> {


	 Page<AuditEntity> findAllByOrderByIdDesc(Pageable pageable);


}
